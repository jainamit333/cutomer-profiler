package com.neo.entity.neo;

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
public class NeoGraph {

    @GraphId
    private Long id;

    private Long zoneId;


    private Long zoneName;

    private String expression;

    @Relationship(type = "AFFECTED_BY")
    private List<NeoEvent> events;

    public NeoGraph(){}
}
