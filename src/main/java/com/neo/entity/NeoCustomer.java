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
public class NeoCustomer {

    @GraphId
    private Long id;

    private Customer customer;

    @Relationship(type = "EVENTS_PERFORMED")
    private NeoCustomerEntityRelationShip customerEntityRelationShip;

    @Relationship(type = "CUSTOMER_GRAPH")
    private List<NeoGraph> graphs;

    @Relationship(type = "CUSTOMER_RELATED_TO_FUNNEL")
    private NeoCustomerFunnelRelationShip neoCustomerFunnelRelationShip;


    private NeoCustomer(){}

}
