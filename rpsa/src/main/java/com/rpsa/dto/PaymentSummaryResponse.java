package com.rpsa.dto;

public class PaymentSummaryResponse {
    private String amount;
    private String paymentInitiatedDate;
    private String periodPaidFor;

    public PaymentSummaryResponse(String amount, String paymentInitiatedDate, String periodPaidFor) {
        this.amount = amount;
        this.paymentInitiatedDate = paymentInitiatedDate;
        this.periodPaidFor = periodPaidFor;
    }

    // Getters
    public String getAmount() {
        return amount;
    }

    public String getPaymentInitiatedDate() {
        return paymentInitiatedDate;
    }

    public String getPeriodPaidFor() {
        return periodPaidFor;
    }
}
