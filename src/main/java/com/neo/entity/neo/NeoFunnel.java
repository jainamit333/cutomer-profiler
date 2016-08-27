package com.neo.entity.neo;

import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Created by amit on 25/8/16.
 */
@NodeEntity
@Data
public class NeoFunnel {

    @GraphId
    private Long id;


    private String funnelName;

    public NeoFunnel(){}

    @Relationship(type = "AFFECTED_BY")
    private List<NeoEvent> events;

    @Relationship(type = "HAS_CUSTOMER")
    private NeoFunnelCustomerRelationShip funnelCustomerRelationShip;



}

