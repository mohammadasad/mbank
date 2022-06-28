package com.mbank.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="payment_details")
@Setter
@Getter
public class PaymentDetailsDTO {
	
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", updatable = false, nullable = false)
	private int paymentId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "tran_date",nullable = false)
	private Date transactionDate; // it will be equal to created date
	@Column(name = "branch_name",nullable = false)
	private String branchName; // it could be branch name or atm from where transaction is being done
	@Column(name = "reference_number",nullable = false)
	private String referenceNumber; // check or any other number, for cash can be null
	@Column(name = "widthdrawl_amount")
	private BigDecimal widthDrawlAmount;
	@Column(name = "deposit_amount")
	private BigDecimal depositAmount;
	@Column(name = "balance_amount",nullable = false)
	private BigDecimal balanceAmount;
	@ManyToOne
	@JoinColumn(name="customer_id")
	CustomerDetailsDTO customerDetails;
	
	@JsonProperty(access=Access.WRITE_ONLY)
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdDate;
	@JsonProperty(access=Access.WRITE_ONLY)
	@Column(nullable = false)
	private int createdBy;
	@JsonProperty(access=Access.WRITE_ONLY)
	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
	private Date modifiedDate;
	@JsonProperty(access=Access.WRITE_ONLY)
	@Column
	private int modifiedBy;
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public BigDecimal getWidthDrawlAmount() {
		return widthDrawlAmount;
	}
	public void setWidthDrawlAmount(BigDecimal widthDrawlAmount) {
		this.widthDrawlAmount = widthDrawlAmount;
	}
	public BigDecimal getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
	}
	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public CustomerDetailsDTO getCustomerDetails() {
		return customerDetails;
	}
	public void setCustomerDetails(CustomerDetailsDTO customerDetails) {
		this.customerDetails = customerDetails;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public int getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
