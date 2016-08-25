package com.neo.entity;

import com.neo.pojo.FunnelType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by amit on 25/8/16.
 */
@Entity
@Data
public class Funnel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String step;

    @Column
    @Enumerated(EnumType.STRING)
    private FunnelType funnelType;

    @Lob
    private String funnelEvaluation;

    @Column
    private Date createdAt;


}
