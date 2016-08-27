package com.neo.controller;

import com.neo.handler.ActionHandler;
import com.neo.handler.MetaDataHandler;
import com.neo.handler.ResponseHandler;
import com.neo.pojo.Response;
import com.neo.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;

/**
 * Created by amit on 9/8/16.
 */
@Controller
@RequestMapping("/api")
public class ApiController {


    @Autowired
    private ActionHandler actionHandler;
    @Autowired
    private MetaDataHandler metaDataHandler;
    @Autowired
    private JsonUtil jsonUtil;


    @RequestMapping(path = "test",method = RequestMethod.GET)
    @ResponseBody
    public String testApi(){
        return "working";
    }

    @RequestMapping(path = "meta/counters",method = RequestMethod.GET)
    @ResponseBody
    public DeferredResult<Response> getObjectsCounters(){
        DeferredResult<Response> responseDeferredResult = new DeferredResult<>(9000l);
        ResponseHandler responseHandler = new ResponseHandler(responseDeferredResult);
        Observable<Response> responseObservable =metaDataHandler.getEntityCounter();
        responseObservable.subscribe(responseHandler);
        return responseDeferredResult;
    }


    @RequestMapping(path = "create/event",method = RequestMethod.POST)
    @ResponseBody
    public Response createResponse(@RequestBody String request){
        return actionHandler.createEvent(jsonUtil.createJsonFromString(request));
    }

    @RequestMapping(path = "create/funnel",method = RequestMethod.POST)
    @ResponseBody
    public Response createFunnel(@RequestBody String request){
        return actionHandler.createFunnel(jsonUtil.createJsonFromString(request));
    }


    @RequestMapping(path = "create/graph",method = RequestMethod.POST)
    @ResponseBody
    public Response createGraph(@RequestBody String request){
        return actionHandler.createGraph(jsonUtil.createJsonFromString(request));
    }

    @RequestMapping(path = "event",method = RequestMethod.POST)
    @ResponseBody
    public Response event(@RequestBody String request){
        return actionHandler.handleCustomerEvent(jsonUtil.createJsonFromString(request));
    }


}
