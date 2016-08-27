package com.neo.repositories.mysql;

import com.neo.entity.Zone;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by amit on 27/8/16.
 */
public interface ZoneRepository extends CrudRepository<Zone,Long> {
}
