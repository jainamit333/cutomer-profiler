package com.neo.engine;

import com.neo.entity.CustomerEvents;
import com.neo.pojo.BucketType;
import org.springframework.stereotype.Component;

/**
 * Created by amit on 27/8/16.
 */
@Component
public class ZoneUpdater implements UpdaterInterface {


    final BucketType bucketType = BucketType.ZONE;

    @Override
    public void executeUpdate(CustomerEvents events, Long executorId) {


    }
}
