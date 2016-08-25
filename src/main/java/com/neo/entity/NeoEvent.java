package com.neo.entity;

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
public class NeoEvent {

    @GraphId
    private Long id;

    @Relationship(type = "EVENT_BY")
    private NeoEventCustomerRelationShip eventCustomerRelationShip;


    @Relationship(type = "EVENT_GRAPH")
    private List<NeoGraph> graphs;

    @Relationship(type = "EVENT_FUNNEL")
    private List<NeoFunnel> funnels;

    private NeoEvent(){}
}
