package com.neo.repositories.mysql;

import com.neo.entity.Funnel;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by amit on 27/8/16.
 */
public interface FunnelRepository extends CrudRepository<Funnel,Long> {
}
