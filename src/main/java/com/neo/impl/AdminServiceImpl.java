package com.neo.impl;

import com.neo.entity.*;
import com.neo.entity.neo.NeoEvent;
import com.neo.entity.neo.NeoFunnel;
import com.neo.entity.neo.NeoGraph;
import com.neo.pojo.FunnelType;
import com.neo.repositories.mysql.*;
import com.neo.repositories.neo.NeoEventRepository;
import com.neo.repositories.neo.NeoFunnelRepository;
import com.neo.repositories.neo.NeoGraphRepository;
import com.neo.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by amit on 26/8/16.
 */
@Component("adminServiceImpl")
public class AdminServiceImpl implements AdminService {


    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private NeoFunnelRepository neoFunnelRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private NeoGraphRepository neoGraphRepository;
    @Autowired
    private NeoEventRepository neoEventRepository;

    @Autowired
    private FunnelRepository funnelRepository;
    @Autowired
    private ZoneRepository zoneRepository;
    @Autowired
    private CustomerEventRepository customerEventRepository;

    @Override
    public Long eventsCounter() {
        return eventRepository.count();
    }

    @Override
    public Long funnelCounter() {
        return neoFunnelRepository.count();
    }

    @Override
    public Long graphCounter() {
        return neoGraphRepository.count();
    }

    @Override
    public Long customerCounter() {
        return customerRepository.count();
    }

    @Override
    public Long createEvent(String name, int score, boolean enableForDuplicate) throws Exception {

        assert !StringUtils.isBlank(name);
        Events events = eventRepository.findByName(name);
        if (events != null) {
            throw new Exception("Event Name Already Present");
        } else {

            try {
                Events events1 = new Events();
                events1.setName(name);
                events1.setEventScore(score);
                events1.setEnableForDuplicate(enableForDuplicate);
                events1.setCreatedAt(new Date());
                eventRepository.save(events1);
                return events1.getId();
            } catch (Exception e) {
                e.printStackTrace();
                return -1l;
            }

        }

    }

    @Override
    public boolean createNeoEvent(String name, int score, boolean enableForDuplicate, Long eventId) {


        try {
            NeoEvent neoEvent = new NeoEvent();
            neoEvent.setEventId(eventId);
            neoEventRepository.save(neoEvent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Long createNeoFunnel(String name, List<Long> eventsAffecting) {

        try {
            List<NeoEvent> neoEvents = neoEventRepository.findByEventIdIn(eventsAffecting);
            NeoFunnel neoFunnel = new NeoFunnel();
            neoFunnel.setFunnelName(name);
            neoFunnel.setEvents(neoEvents);
            neoFunnelRepository.save(neoFunnel);
            return neoFunnel.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1l;
        }

    }

    @Override
    public void updateFunnelSteps(Long funnelId, String stepName, FunnelType scoreBased, Long stepStartValue, Long stepEndValue) {


        try {
            Funnel funnel = new Funnel();
            funnel.setCreatedAt(new Date());
            funnel.setEndScore(stepEndValue);
            funnel.setStartScore(stepStartValue);
            funnel.setEvaluationExpression(null);
            funnel.setFunnelType(scoreBased);
            funnel.setStep(stepName);
            funnel.setFunnelId(funnelId);
            funnelRepository.save(funnel);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public Long createZone(String name, String expression) {
        try {

            Zone zone = new Zone();
            zone.setName(name);
            zone.setZoneEvaluation(expression);
            zone.setCreatedAt(new Date());
            zoneRepository.save(zone);
            return zone.getId();


        } catch (Exception e) {
            e.printStackTrace();
            return -1l;
        }
    }

    @Override
    public void createNeoZone(Long zoneId, String name, String expression, List<Long> eventsAffecting) {


        try {
            NeoGraph neoGraph = new NeoGraph();
            List<NeoEvent> neoEvents = neoEventRepository.findByEventIdIn(eventsAffecting);
            neoGraph.setZoneId(zoneId);
            neoGraph.setExpression(expression);
            neoGraph.setEvents(neoEvents);
            neoGraphRepository.save(neoGraph);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Long getOrCreateCustomer(String customerId) {

        Customer customer = customerRepository.findByCustomerId(customerId);
        if (customer == null) {
            customer = new Customer();
            customer.setCustomerId(customerId);
            customer.setCreatedAt(new Date());
            customerRepository.save(customer);
        }
        return customer.getId();

    }

    @Override
    public Long getEventId(String name) throws Exception {
        Events events = eventRepository.findByName(name);
        if (events == null) {
            throw new Exception("No Event present by this name");
        } else {
            return events.getId();
        }
    }

    @Override
    public Long createCustomerEventEntry(Long customer, Long eventId, Integer score, Boolean considerForFunnel, Boolean considerForZone, Boolean duplicateConsideration) {


        assert customer != null;
        assert eventId != null;

        CustomerEvents customerEvents = new CustomerEvents();
        customerEvents.setConsiderForFunnel(considerForFunnel);
        customerEvents.setConsiderForZone(considerForZone);
        customerEvents.setCreatedAt(new Date());
        customerEvents.setCustomerId(customer);
        customerEvents.setEventId(eventId);
        customerEventRepository.save(customerEvents);
        return customerEvents.getId();

    }


}
