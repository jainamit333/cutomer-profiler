package com.neo.entity.neo;

import org.neo4j.ogm.annotation.*;

/**
 * Created by amit on 25/8/16.
 */
@RelationshipEntity(type = "HAS_CUSTOMER")
public class NeoFunnelCustomerRelationShip {

    @GraphId
    private Long relationshipId;

    @Property
    private String step;

    @EndNode
    private NeoCustomer customer;

    @StartNode
    private NeoFunnel funnel;
}
