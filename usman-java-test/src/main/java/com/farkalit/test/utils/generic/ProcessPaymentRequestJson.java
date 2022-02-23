package com.farkalit.test.utils.generic;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcessPaymentRequestJson {

    private String requestId;
    private String orderId;
    private String siteId;
    private String consignmentId;
    private Double amount;
    private String transactionId;
    private String currency;
    private String paymentMethod;
    private String transactionType;
    private String paymentProvider;
    private Object cardType;

    public String getRequestId() {
      return requestId;
    }

    public void setRequestId(String requestId) {
      this.requestId = requestId;
    }

    public String getOrderId() {
      return orderId;
    }

    public void setOrderId(String orderId) {
      this.orderId = orderId;
    }

    public String getSiteId() {
      return siteId;
    }

    public void setSiteId(String siteId) {
      this.siteId = siteId;
    }

    public String getConsignmentId() {
      return consignmentId;
    }

    public void setConsignmentId(String consignmentId) {
      this.consignmentId = consignmentId;
    }

    public Double getAmount() {
      return amount;
    }

    public void setAmount(Double amount) {
      this.amount = amount;
    }

    public String getTransactionId() {
      return transactionId;
    }

    public void setTransactionId(String transactionId) {
      this.transactionId = transactionId;
    }

    public String getCurrency() {
      return currency;
    }

    public void setCurrency(String currency) {
      this.currency = currency;
    }

    public String getPaymentMethod() {
      return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
      this.paymentMethod = paymentMethod;
    }

    public String getTransactionType() {
      return transactionType;
    }

    public void setTransactionType(String transactionType) {
      this.transactionType = transactionType;
    }

    public String getPaymentProvider() {
      return paymentProvider;
    }

    public void setPaymentProvider(String paymentProvider) {
      this.paymentProvider = paymentProvider;
    }

    public Object getCardType() {
      return cardType;
    }

    public void setCardType(Object cardType) {
      this.cardType = cardType;
    }

    @Override
    public String toString() {
      return "ProcessPaymentRequestJson [requestId=" + requestId + ", orderId=" + orderId
          + ", siteId=" + siteId + ", consignmentId=" + consignmentId + ", amount=" + amount
          + ", transactionId=" + transactionId + ", currency=" + currency + ", paymentMethod="
          + paymentMethod + ", transactionType=" + transactionType + ", paymentProvider="
          + paymentProvider + ", cardType=" + cardType + "]";
    }

}
