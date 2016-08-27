package com.neo.entity.neo;

import org.neo4j.ogm.annotation.*;

import javax.persistence.Lob;

/**
 * Created by amit on 27/8/16.
 */
@RelationshipEntity(type = "HAS_CUSTOMER")
public class NeoZoneCustomerREelationShip {

    @GraphId
    private Long relationshipId;

    @Property
    @Lob
    private String  expression;

    @EndNode
    private NeoCustomer customer;

    @StartNode
    private NeoGraph graph;
}
