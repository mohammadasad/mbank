package com.mbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbank.entity.ApiResponse;
import com.mbank.entity.CustomerDetailsDTO;
import com.mbank.entity.PaymentDetailsDTO;
import com.mbank.service.MbankService;

@RestController
@RequestMapping("/mbank")
public class MbankController {
	
	@Autowired
	MbankService mbankService;
	
	// this api will be used to create new account 
	@PostMapping("/createAccount")
	public ApiResponse createAccount(@RequestBody CustomerDetailsDTO customerDetailsDTO){
		return mbankService.createAccount(customerDetailsDTO);
	}

	// this api will be used to deposity amount
	@PostMapping("/depositPayment")
	public ApiResponse depositPayment(@RequestBody PaymentDetailsDTO paymentDetailsDTO){
		return mbankService.depositPayment(paymentDetailsDTO);
	}
	// this api will be used to widthdrawl amount
		@PostMapping("/widthdrawlPayment")
		public ApiResponse widthdrawlPayment(@RequestBody PaymentDetailsDTO paymentDetailsDTO){
			return mbankService.widthdrawlPayment(paymentDetailsDTO);
		}

}
