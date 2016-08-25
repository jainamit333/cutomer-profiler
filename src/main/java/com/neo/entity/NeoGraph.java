package com.neo.entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Created by amit on 25/8/16.
 */
@NodeEntity
public class NeoGraph {

    @GraphId
    private Long id;

    @Relationship(type = "AFFECTED_BY")
    private List<NeoEvent> events;

    private NeoGraph(){}
}
