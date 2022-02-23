package com.farkalit.test.utils.generic;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RetryApiRequest {

  private String apiUrl;

  private String method;

  private String requestBody;

  private int counter;

  private int threshold;

  private Class<?> requestType;

  private Class<?> responseType;

  private List<HeaderKeyValue> headerKeyValue;


  public String getApiUrl() {
    return apiUrl;
  }

  public void setApiUrl(String apiUrl) {
    this.apiUrl = apiUrl;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getRequestBody() {
    return requestBody;
  }

  public void setRequestBody(String requestBody) {
    this.requestBody = requestBody;
  }

  public int getCounter() {
    return counter;
  }

  public void setCounter(int counter) {
    this.counter = counter;
  }

  public int getThreshold() {
    return threshold;
  }

  public void setThreshold(int threshold) {
    this.threshold = threshold;
  }


  public Class<?> getRequestType() {
    return requestType;
  }

  public void setRequestType(Class<?> requestType) {
    this.requestType = requestType;
  }

  public Class<?> getResponseType() {
    return responseType;
  }

  public void setResponseType(Class<?> responseType) {
    this.responseType = responseType;
  }


  public List<HeaderKeyValue> getHeaderKeyValue() {
    return headerKeyValue;
  }

  public void setHeaderKeyValue(List<HeaderKeyValue> headerKeyValue) {
    this.headerKeyValue = headerKeyValue;
  }

  @Override
  public String toString() {
    return "RetryApiRequest [apiUrl=" + apiUrl + ", method=" + method + ", requestBody="
        + requestBody + ", counter=" + counter + ", threshold="
        + threshold + ", requestType=" + requestType + ", responseType=" + responseType
        + ", headerKeyValue=" + headerKeyValue + "]";
  }


}
