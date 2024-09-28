package com.javarish.api.repositroy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javarish.api.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Payment findByOrderId(int orderId);
    
    
    List<Payment> findByPaymentIdIn(List<Long> ids);


}
