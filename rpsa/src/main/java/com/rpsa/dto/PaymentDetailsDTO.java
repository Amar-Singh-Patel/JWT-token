package com.rpsa.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@AllArgsConstructor


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailsDTO {
	
	@JsonProperty("amount")
    private String amount;
    @JsonProperty("periodPaidFor")
    private String periodPaidFor;

    @JsonProperty("paymentInitiatedDate")
    private String paymentInitiatedDate;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPeriodPaidFor() {
		return periodPaidFor;
	}

	public void setPeriodPaidFor(String periodPaidFor) {
		this.periodPaidFor = periodPaidFor;
	}

	public String getPaymentInitiatedDate() {
		return paymentInitiatedDate;
	}

	public void setPaymentInitiatedDate(String paymentInitiatedDate) {
		this.paymentInitiatedDate = paymentInitiatedDate;
	}
}
