package com.mbank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mbank.entity.PaymentDetailsDTO;

@Repository
public interface PaymentDao extends JpaRepository<PaymentDetailsDTO, Integer> {

	PaymentDetailsDTO findFirstByOrderByPaymentIdDesc();
}
