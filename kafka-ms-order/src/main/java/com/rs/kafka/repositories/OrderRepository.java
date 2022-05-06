package com.rs.kafka.repositories;

import com.rs.kafka.entity.*;
import org.springframework.data.repository.*;

/**
 * created by rs 5/6/2022.
 */
//@Repository
public interface OrderRepository extends CrudRepository<Order,Integer> {
}
