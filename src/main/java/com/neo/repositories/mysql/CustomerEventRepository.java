package com.neo.repositories.mysql;

import com.neo.entity.CustomerEvents;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by amit on 27/8/16.
 */
public interface CustomerEventRepository extends CrudRepository<CustomerEvents,Long>{

}
