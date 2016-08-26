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
public class Events implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private Boolean enableForDuplicate;

    @Column
    private Integer eventScore;

    @Column
    private Date createdAt;

}
