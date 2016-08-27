package com.neo.repositories.mysql;

import com.neo.entity.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by amit on 26/8/16.
 */

public interface CustomerRepository extends CrudRepository<Customer,Long> {


    Customer findByCustomerId(String customerId);
}
