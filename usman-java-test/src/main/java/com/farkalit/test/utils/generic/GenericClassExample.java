package com.farkalit.test.utils.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.fasterxml.jackson.databind.ObjectMapper;


public class GenericClassExample {

  public static void main(String[] args) {

    // Map<String, Class<?>> props = new HashMap<>();
    // props.put("requestType", Integer.class);
    // props.put("responseType", String.class);
    //
    // for (Entry<String, Class<?>> entry : props.entrySet()) {
    // System.out.println("keyName:" + entry.getKey());
    // if (entry.getKey().equalsIgnoreCase("requestType")) {
    // System.out.println("keyValue:" + entry.getValue());
    // }
    // }
    ProcessPaymentRequestJson jsonBody = new ProcessPaymentRequestJson();
    jsonBody.setAmount(new Double(11.23));
    jsonBody.setCurrency("AED");
    jsonBody.setOrderId("1232613213");
    jsonBody.setTransactionId("1222000000123123");
    jsonBody.setTransactionType("SALE");
    jsonBody.setPaymentMethod("creditcard");
    jsonBody.setPaymentProvider("Cybersource");
    jsonBody.setSiteId("mafuae");

    RetryApiRequest retryRequest = new RetryApiRequest();
    retryRequest.setCounter(0);
    retryRequest.setThreshold(5);
    retryRequest.setApiUrl("https://paymentselector-service.azurewebsites.net/api/refund");


    retryRequest.setRequestType(ProcessPaymentRequestJson.class);
    retryRequest.setResponseType(String.class);

    HeaderKeyValue keyValue1 = new HeaderKeyValue("x-api-key", "sdfghjkl;kjhgghj");
    HeaderKeyValue keyValue2 = new HeaderKeyValue("channel-code", "cityplus");
    List<HeaderKeyValue> listHeader = new ArrayList<>();
    listHeader.add(keyValue1);
    listHeader.add(keyValue2);
    retryRequest.setHeaderKeyValue(listHeader);


    try {
      ObjectMapper objectMapper = new ObjectMapper();
      retryRequest.setRequestBody(objectMapper.writeValueAsString(jsonBody));
      parseJsonData(objectMapper.writeValueAsString(retryRequest));
    } catch (Exception e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }

  }

  public static void parseJsonData(String message) throws Exception {
    System.out.println("Inside method MafUaePaymentRetryTriggerFunction message:" + message);
    RetryApiRequest requestBody = new ObjectMapper().readValue(message, RetryApiRequest.class);
    System.out.println("MafUaePaymentRetryTriggerFunction requestBody:" + requestBody);

    Class<?> requestType = requestBody.getRequestType();
    System.out.println("req-class:" + requestType);

    ProcessPaymentRequestJson processRequest = new ProcessPaymentRequestJson();
    if (requestType.isInstance(processRequest)) {
      System.out.println("is class ProcessPaymentRequestJson:" + true);
      processRequest = new ObjectMapper().readValue(requestBody.getRequestBody(),
          ProcessPaymentRequestJson.class);
      System.out.println("req-obj:" + processRequest);
    }

    String response = "";
    Class<?> responseType = requestBody.getResponseType();
    if (responseType.isInstance(response)) {
      System.out.println("is class String:" + true);
    }

  }
}
