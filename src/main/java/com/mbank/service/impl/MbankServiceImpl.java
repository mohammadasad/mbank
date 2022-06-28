package com.mbank.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mbank.dao.MbankDao;
import com.mbank.dao.PaymentDao;
import com.mbank.entity.ApiResponse;
import com.mbank.entity.CustomerDetailsDTO;
import com.mbank.entity.PaymentDetailsDTO;
import com.mbank.exception.RecordNotFoundException;
import com.mbank.service.MbankService;
import com.mbank.util.MbankUtil;

@Transactional
@Service
public class MbankServiceImpl implements MbankService {

	private static final Logger logger  = LogManager.getLogger(MbankServiceImpl.class);
	
	@Autowired
	MbankDao mbankDao;
	
	@Autowired
	PaymentDao paymentDao;
	
	@Autowired
	MbankUtil mbankUtil;
	
	PaymentDetailsDTO pdDTO=null;
	
	String msg=null;
	
	@Override
	public ApiResponse createAccount(CustomerDetailsDTO customerDetailsDTO) {
		if(logger.isDebugEnabled())
			logger.debug("MbankServiceImpl.createAccount():::Start::");
		String accoutNumber=mbankUtil.createRandomAccountnumber();
		customerDetailsDTO.setAccountNumber(accoutNumber);
		customerDetailsDTO.setCreatedDate(new Date());
		customerDetailsDTO.setModifiedDate(new Date());
		
		mbankDao.save(customerDetailsDTO);
		
		 msg="Successfully Account Created, Account Number is --"+accoutNumber;
		
		return new ApiResponse(HttpStatus.OK.value(), msg, null);
	}

	@Override
	public ApiResponse depositPayment(PaymentDetailsDTO paymentDetailsDTO) {
		if(logger.isDebugEnabled())
			logger.debug("MbankServiceImpl.depositPayment():::Start::");
		pdDTO = paymentDao.findFirstByOrderByPaymentIdDesc();
		BigDecimal balance=new BigDecimal(0);
		if(pdDTO!=null){
			balance=pdDTO.getBalanceAmount().add(paymentDetailsDTO.getDepositAmount());
		}
		else{
			balance=paymentDetailsDTO.getDepositAmount();
		}
		paymentDetailsDTO.setWidthDrawlAmount(new BigDecimal(0));
		paymentDetailsDTO.setTransactionDate(new Date());
		paymentDetailsDTO.setBalanceAmount(balance);
		paymentDetailsDTO.setCreatedBy(1);
		paymentDetailsDTO.setCreatedDate(new Date());
		paymentDetailsDTO.setModifiedDate(new Date());
		paymentDao.save(paymentDetailsDTO);
		 msg="Successfully deposited , balance is --"+balance;
		  return new ApiResponse(HttpStatus.OK.value(), msg, null);
	}

	@Override
	public ApiResponse widthdrawlPayment(PaymentDetailsDTO paymentDetailsDTO) {
		if(logger.isDebugEnabled())
			logger.debug("MbankServiceImpl.widthdrawlPayment():::Start::");
		pdDTO = paymentDao.findFirstByOrderByPaymentIdDesc();
		BigDecimal balance=new BigDecimal(0);
		if(pdDTO!=null){
			balance=pdDTO.getBalanceAmount().subtract(paymentDetailsDTO.getWidthDrawlAmount());
		}
		if(balance.compareTo(new BigDecimal(0))<0){
			throw new RecordNotFoundException("Failed !! You have insufficiet balance");
		}
		paymentDetailsDTO.setDepositAmount(new BigDecimal(0));
		paymentDetailsDTO.setTransactionDate(new Date());
		paymentDetailsDTO.setBalanceAmount(balance);
		paymentDetailsDTO.setCreatedBy(1);
		paymentDetailsDTO.setCreatedDate(new Date());
		paymentDetailsDTO.setModifiedDate(new Date());
		paymentDao.save(paymentDetailsDTO);
		 msg="Successfully widthdrawl , balance is --"+balance;
		return new ApiResponse(HttpStatus.OK.value(), msg, null);
	}
	
}
