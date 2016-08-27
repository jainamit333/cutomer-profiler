package com.neo.repositories.mysql;

import com.neo.entity.EventEngineReport;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by amit on 27/8/16.
 */
public interface EventReporterRepository extends CrudRepository<EventEngineReport,Long> {
}
