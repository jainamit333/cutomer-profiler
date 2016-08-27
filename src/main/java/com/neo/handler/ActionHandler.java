package com.neo.handler;

import com.neo.engine.EventUpdater;
import com.neo.pojo.Error;
import com.neo.pojo.FunnelType;
import com.neo.pojo.Response;
import com.neo.service.AdminService;
import com.neo.util.JsonUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amit on 26/8/16.
 */
@Component
public class ActionHandler {

    @Autowired
    private JsonUtil jsonUtil;
    @Autowired
    @Qualifier("adminServiceImpl")
    private AdminService adminService;
    @Autowired
    private EventUpdater eventUpdater;

    public Response createEvent(JSONObject jsonObject) {

        //add value to sql
        //add node to neo
        //link valid funnels and graphs
        Response response = new Response();
        boolean eventCreated = false;

        try {

            String name = (String) jsonUtil.getValue(jsonObject, "name");
            int score = (Integer) jsonUtil.getValue(jsonObject, "score");
            boolean enableForDuplicate = (Boolean) jsonUtil.getValue(jsonObject, "enableForDuplicate");

            Long eventId = adminService.createEvent(name, score, enableForDuplicate);
            if (eventId.compareTo(new Long(0l)) >= 0) {
                eventCreated = adminService.createNeoEvent(name, score, enableForDuplicate, eventId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Error error = new Error();
            error.setCode("1000");
            error.setMessage(e.getMessage());
            response.setError(error);
        } finally {
            response.setResponse(eventCreated);
            return response;
        }
    }

    public Response createFunnel(JSONObject jsonObject) {

        Response response = new Response();
        boolean funnelCreated = false;

        try {
            String name = (String) jsonUtil.getValue(jsonObject, "name");
            JSONArray steps = (JSONArray) jsonUtil.getValue(jsonObject, "steps");
            JSONArray eventsAffecting = (JSONArray) jsonUtil.getValue(jsonObject, "events_affecting");
            List<Long> eventsAffectingList = new ArrayList<>();
            while (eventsAffecting.iterator().hasNext()) {
                eventsAffectingList.add((Long) eventsAffecting.iterator().next());
            }
            Long funnelId = adminService.createNeoFunnel(name, eventsAffectingList);
            if (funnelId.compareTo(new Long(0)) >= 0) {

                while (steps.iterator().hasNext()) {

                    JSONObject step = (JSONObject) steps.iterator().next();
                    String stepName = (String) jsonUtil.getValue(step, "step_name");
                    Long stepStartValue = (Long) jsonUtil.getValue(step, "step_start_score");
                    Long stepEndValue = (Long) jsonUtil.getValue(step, "step_end_score");
                    adminService.updateFunnelSteps(funnelId,stepName, FunnelType.SCORE_BASED,stepStartValue,stepEndValue);


                }
                funnelCreated = true;
                response.setResponse(funnelCreated);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Error error = new Error();
            error.setCode("1000");
            error.setMessage(e.getMessage());
            response.setError(error);
        } finally {
            return response;
        }

    }

    public Response createGraph(JSONObject jsonObject) {
        Response response = new Response();
        boolean graphCreated = false;

        try{

            String name = (String) jsonUtil.getValue(jsonObject, "name");
            String expression = (String) jsonUtil.getValue(jsonObject, "expression");
            JSONArray eventsAffecting = (JSONArray) jsonUtil.getValue(jsonObject, "events_affecting");
            Long zoneId = adminService.createZone(name,expression);
            if(zoneId.compareTo(new Long(0l))>=0){
                List<Long> eventsAffectingList = new ArrayList<>();
                while (eventsAffecting.iterator().hasNext()) {
                    eventsAffectingList.add((Long) eventsAffecting.iterator().next());
                }
                adminService.createNeoZone(zoneId,name,expression,eventsAffectingList);
            }
            graphCreated = true;
            response.setResponse(graphCreated);

        }catch (Exception e){
            e.printStackTrace();
            Error error = new Error();
            error.setCode("1000");
            error.setMessage(e.getMessage());
            response.setError(error);
        }finally {
            return response;
        }

    }

    public Response handleCustomerEvent(JSONObject jsonObject) {
        Response response = new Response();
        boolean graphCreated = false;
        try{

            String name = (String) jsonUtil.getValue(jsonObject, "name");
            Integer score = (Integer) jsonUtil.getValue(jsonObject, "score");
            Boolean considerForFunnel = (Boolean)jsonUtil.getValue(jsonObject, "considerForFunnel");
            Boolean considerForZone = (Boolean)jsonUtil.getValue(jsonObject, "considerForZone");
            Boolean duplicateConsideration = (Boolean)jsonUtil.getValue(jsonObject, "duplicateConsideration");
            String customerId = (String) jsonUtil.getValue(jsonObject, "customerId");
            //check if customer present then get id or create customer
            // TODO: 27/8/16 below too steps can be call in parallel
            Long customer = adminService.getOrCreateCustomer(customerId);
            Long eventId = adminService.getEventId(name);
            Long customerEventId = adminService.createCustomerEventEntry(customer,eventId,score,considerForFunnel,considerForZone,duplicateConsideration);
            eventUpdater.registerNewUpdate(customerEventId);
            graphCreated = true;
        }catch (Exception e){
            e.printStackTrace();
            Error error = new Error();
            error.setCode("1000");
            error.setMessage(e.getMessage());
            response.setError(error);

        }finally {
            response.setResponse(graphCreated);
            return response;
        }
    }
}
