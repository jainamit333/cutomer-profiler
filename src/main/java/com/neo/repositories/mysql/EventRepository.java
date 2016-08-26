package com.neo.repositories.mysql;

import com.neo.entity.Events;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by amit on 26/8/16.
 */
public interface EventRepository extends CrudRepository<Events,Long> {

    Events findByName(String name);
}
