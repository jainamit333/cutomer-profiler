package com.neo.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.util.List;

/**
 * Created by amit on 25/8/16.
 */
@RelationshipEntity(type = "EVENT_BY")
@Data
public class NeoEventCustomerRelationShip {



    @GraphId
    private Long relationshipId;

    @Property
    private List<String> tags;

    @EndNode
    private  NeoCustomer customer;

    @StartNode
    private NeoEvent event;
}
