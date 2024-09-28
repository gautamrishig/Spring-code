package com.javarishi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javarishi.api.entity.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {

}
