package com.neo.entity.neo;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

/**
 * Created by amit on 25/8/16.
 */
@RelationshipEntity(type = "CUSTOMER_RELATED_TO_FUNNEL")
@Data
public class NeoCustomerFunnelRelationShip {

    @GraphId
    private Long relationshipId;

    @Property
    private String step;

    @StartNode
    private NeoCustomer customer;

    @EndNode
    private NeoFunnel funnel;



}
