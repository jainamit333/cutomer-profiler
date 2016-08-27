package com.neo.engine;

import com.neo.entity.CustomerEvents;
import com.neo.pojo.BucketType;
import com.neo.pojo.EventReporterStatus;
import com.neo.repositories.mysql.CustomerEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by amit on 27/8/16.
 */
@Component
public class EventUpdater {

    @Autowired
    private EventReporter eventEngineReport;


    @Autowired
    private CustomerEventRepository customerEventRepository;
    @Autowired
    private ZoneUpdater zoneUpdater;
    @Autowired
    private FunnelUpdater funnelUpdater;

    //this method will submit the task to kakfa/redis queue
    // TODO: 27/8/16 currently just making it async , in future push it to qouue \
    @Async
    public void registerNewUpdate(Long eventId) {

        //check if event enable for funnel
        //then call funnel update
        //check if enable for grapgh enable
        //then call graph enable
        CustomerEvents events = customerEventRepository.findOne(eventId);
        if (events == null) {
            eventEngineReport.registerNewEvent(eventId, "ANY", "EVENT NOT FOUND", EventReporterStatus.ERROR);
        } else {

            if (events.getConsiderForFunnel()) {
                Long funnelExecutorId = eventEngineReport.registerNewEvent(eventId, "FUNNEL", "STARTING EVENT UPDATE", EventReporterStatus.STARTING);
                executeUpdate(events, funnelExecutorId, BucketType.FUNNEL);
            }
            if (events.getConsiderForZone()) {
                Long graphExecutorId = eventEngineReport.registerNewEvent(eventId, "ZONE", "STARTING EVENT UPDATE", EventReporterStatus.STARTING);
                executeUpdate(events, graphExecutorId, BucketType.ZONE);
            }

        }

    }

    @Async
    private void executeUpdate(CustomerEvents events, Long funnelExecutorId, BucketType funnel) {


        switch (funnel) {
            case FUNNEL:
                funnelUpdater.executeUpdate(events, funnelExecutorId);
                break;
            case ZONE:
                zoneUpdater.executeUpdate(events, funnelExecutorId);
                break;
            default:
                //// TODO: 27/8/16 log invalid bucket type

        }

    }

}
