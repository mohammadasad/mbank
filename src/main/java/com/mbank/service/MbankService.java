package com.mbank.service;

import com.mbank.entity.ApiResponse;
import com.mbank.entity.CustomerDetailsDTO;
import com.mbank.entity.PaymentDetailsDTO;

public interface MbankService {
	ApiResponse createAccount(CustomerDetailsDTO customerDetailsDTO);
	ApiResponse depositPayment(PaymentDetailsDTO paymentDetailsDTO);
	ApiResponse widthdrawlPayment(PaymentDetailsDTO paymentDetailsDTO);
}
