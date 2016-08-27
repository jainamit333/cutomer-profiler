package com.neo.entity.neo;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

import javax.persistence.Lob;

/**
 * Created by amit on 27/8/16.
 */
@RelationshipEntity(type = "CUSTOMER_RELATED_TO_ZONE")
@Data
public class NeoCustomerZoneRelationShip {



    @GraphId
    private Long relationshipId;

    @Property
    @Lob
    private String expression;

    @StartNode
    private NeoCustomer customer;

    @EndNode
    private NeoFunnel funnel;
}
