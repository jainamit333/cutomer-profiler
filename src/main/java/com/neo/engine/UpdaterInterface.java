package com.neo.engine;

import com.neo.entity.CustomerEvents;

/**
 * Created by amit on 27/8/16.
 */
public interface UpdaterInterface {


    void executeUpdate(CustomerEvents events, Long executorId);
}
