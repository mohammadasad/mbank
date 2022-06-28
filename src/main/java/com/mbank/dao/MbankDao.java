package com.mbank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mbank.entity.CustomerDetailsDTO;

@Repository
public interface MbankDao extends JpaRepository<CustomerDetailsDTO, Integer> {

}
