package com.neo.repositories.neo;

import com.neo.entity.neo.NeoEvent;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by amit on 26/8/16.
 */
public interface NeoEventRepository extends GraphRepository<NeoEvent> {

    NeoEvent findByEventId(Long eventId);
    List<NeoEvent> findByEventIds(List<Long> eventId);


}
