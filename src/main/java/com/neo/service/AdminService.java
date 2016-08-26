package com.neo.service;

/**
 * Created by amit on 26/8/16.
 */

public interface AdminService {


    Long eventsCounter();
    Long funnelCounter();
    Long graphCounter();
    Long customerCounter();

    Long createEvent(String name,int score,boolean enableForDuplicate) throws Exception;
    boolean createNeoEvent(String name, int score, boolean enableForDuplicate,Long eventId);

}
