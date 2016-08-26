package com.neo.repositories.neo;

import com.neo.entity.NeoGraph;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by amit on 26/8/16.
 */
public interface NeoGraphRepository extends CrudRepository<NeoGraph,Long> {

    Long countById();

}
