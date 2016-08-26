package com.neo.handler;

import com.neo.pojo.Error;
import com.neo.pojo.Response;
import com.neo.service.AdminService;
import com.neo.util.JsonUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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

            Long eventId =adminService.createEvent(name, score, enableForDuplicate);
            if (eventId.compareTo(new Long(0l)) >=0) {
                eventCreated = adminService.createNeoEvent(name, score, enableForDuplicate,eventId);
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
}
