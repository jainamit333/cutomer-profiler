package com.neo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by amit on 25/8/16.
 */
@Entity
@Table
@Data
public class Customer implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String customerId;


    @Column
    private Date createdAt;

}
