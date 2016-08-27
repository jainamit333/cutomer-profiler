package com.neo.entity;

import com.neo.pojo.EventReporterStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by amit on 27/8/16.
 */
@Entity
@Data
public class EventEngineReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long eventId;

    @Column
    private String eventFor;


    @Column
    @Enumerated(EnumType.STRING)
    private EventReporterStatus status;

    @Column
    private Date createdAt;

    @Column
    @Lob
    private String message;


}
