package com.neo.entity.neo;

import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Created by amit on 25/8/16.
 */
@NodeEntity(label = "neo_event")
@Data
public class NeoEvent{

    @GraphId
    private Long id;

    private Long eventId;

    @Relationship(type = "EVENT_BY")
    private NeoEventCustomerRelationShip eventCustomerRelationShip;


    @Relationship(type = "EVENT_GRAPH")
    private List<NeoGraph> graphs;

    @Relationship(type = "EVENT_FUNNEL")
    private List<NeoFunnel> funnels;

}
