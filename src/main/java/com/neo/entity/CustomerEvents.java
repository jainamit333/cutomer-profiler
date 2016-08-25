package com.neo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by amit on 25/8/16.
 */
@Entity
@Data
public class CustomerEvents implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer eventId;

    @Column
    private Integer customerId;

    @Column
    private Date createdAt;

    @Column
    private Boolean considerForFunnel;

    @Column
    private Boolean considerForZone;

    @Column
    private Boolean duplicateConsideration;

    @Column
    private Integer eventScoreOverriden;



}
