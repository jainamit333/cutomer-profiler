package com.neo.handler;

import com.neo.pojo.Response;
import com.neo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rx.Observable;
import com.neo.pojo.Error;

/**
 * Created by amit on 26/8/16.
 */
@Component
public class MetaDataHandler {


    @Autowired
    private AdminService adminService;

    public Observable<Response> getEntityCounter(){

        Observable<Long> customerCounter = Observable.just(adminService.customerCounter());
        Observable<Long> eventCounter = Observable.just(adminService.eventsCounter());
        Observable<Long> funnelCounter = Observable.just(adminService.funnelCounter());
        Observable<Long> graphCounter = Observable.just(adminService.graphCounter());
        return Observable.zip(customerCounter,eventCounter,funnelCounter,graphCounter,(a,b,c,d) ->{
            Response response = new Response();
            try {
                StringBuilder builder = new StringBuilder();
                builder.append("{ \"customer\" : ").append(a).append(", \"event\" :").append(b).append(",\"funnel\" :").append(c).append(",\"graph\":").append(d).append("}");
                response.setResponse(builder.toString());
            }catch (Exception e){
                Error error = new Error();
                error.setCode("1000");
                error.setMessage(e.getMessage());
                response.setError(error);
            }finally {
                return response;
            }

        });

    }

}
