package com.neo.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.util.List;

/**
 * Created by amit on 25/8/16.
 */

@RelationshipEntity(type = "EVENTS_PERFORMED")
@Data
public class NeoCustomerEntityRelationShip {


    @GraphId
    private Long relationshipId;

    @Property
    private List<String> tags;

    @StartNode
    private  NeoCustomer customer;

    @EndNode
    private NeoEvent event;




}
