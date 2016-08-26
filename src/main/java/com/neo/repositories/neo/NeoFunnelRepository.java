package com.neo.repositories.neo;

import com.neo.entity.NeoFunnel;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by amit on 26/8/16.
 */
public interface NeoFunnelRepository extends CrudRepository<NeoFunnel,Long> {

    Long countById();

}
