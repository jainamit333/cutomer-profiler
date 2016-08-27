package com.neo.service;

import com.neo.pojo.FunnelType;

import java.util.List;

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

    Long createNeoFunnel(String name, List<Long> eventsAffecting);

    void updateFunnelSteps(Long funnelId, String stepName, FunnelType scoreBased, Long stepStartValue, Long stepEndValue);

    Long createZone(String name, String expression);

    void createNeoZone(Long zoneId, String name, String expression, List<Long> eventsAffecting);

    Long getOrCreateCustomer(String customerId);

    Long getEventId(String name) throws Exception;

    Long createCustomerEventEntry(Long customer, Long eventId, Integer score, Boolean considerForFunnel, Boolean considerForZone, Boolean duplicateConsideration);
}
