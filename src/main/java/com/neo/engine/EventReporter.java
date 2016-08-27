package com.neo.engine;

import com.neo.entity.EventEngineReport;
import com.neo.pojo.EventReporterStatus;
import com.neo.repositories.mysql.EventReporterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by amit on 27/8/16.
 */
@Component
public class EventReporter {


    @Autowired
    private EventReporterRepository eventReporterRepository;



    public Long registerNewEvent(Long eventId, String typeOfBucket, String message, EventReporterStatus status) {


        EventEngineReport eventEngineReport = new EventEngineReport();
        eventEngineReport.setCreatedAt(new Date());
        eventEngineReport.setEventFor(typeOfBucket);
        eventEngineReport.setEventId(eventId);
        eventEngineReport.setMessage(message);
        eventEngineReport.setStatus(status);
        return eventEngineReport.getId();

    }
}
