package com.neo.impl;

import com.neo.entity.Events;
import com.neo.entity.NeoEvent;
import com.neo.repositories.mysql.CustomerRepository;
import com.neo.repositories.mysql.EventRepository;
import com.neo.repositories.neo.NeoEventRepository;
import com.neo.repositories.neo.NeoFunnelRepository;
import com.neo.repositories.neo.NeoGraphRepository;
import com.neo.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

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
            neoEvent.setId(eventId);
            neoEventRepository.save(neoEvent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


}
