package com.neo.engine;

import com.neo.entity.CustomerEvents;
import com.neo.entity.neo.NeoEvent;
import com.neo.pojo.BucketType;
import com.neo.repositories.neo.NeoEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by amit on 27/8/16.
 */
@Component
public class FunnelUpdater implements UpdaterInterface {

    final BucketType bucketType = BucketType.FUNNEL;

    @Autowired
    private NeoEventRepository  neoEventRepository;


    @Override
    public void executeUpdate(CustomerEvents events, Long executorId) {

        Long eventId = events.getEventId();
        Long customerId = events.getCustomerId();

        //find list of affected funnels
        NeoEvent neoEvent = neoEventRepository.findByEventId(eventId);



    }
}
