package com.neo.handler;

import com.neo.pojo.Response;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Subscriber;

/**
 * Created by amit on 26/8/16.
 */
public class ResponseHandler extends Subscriber<Response> {

    DeferredResult<Response> responseDeferredResult ;
    Response response;
    public ResponseHandler(DeferredResult<Response> responseDeferredResult) {
            this.responseDeferredResult = responseDeferredResult;
    }

    @Override
    public void onCompleted() {
        responseDeferredResult.setResult(this.response);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onNext(Response response) {
        this.response = response;
    }
}
