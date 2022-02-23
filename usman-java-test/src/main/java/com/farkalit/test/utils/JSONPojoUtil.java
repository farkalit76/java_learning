package com.farkalit.test.utils;

import com.farkalit.test.models.RootData;
import com.farkalit.test.models.RootData2;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

/**
 * @author farkalitusman
 *
 */
public class JSONPojoUtil {

  final static String jsondata =
      "{\"card_number\":\"4111111111111111\",\"bill_to_email\":\"c4d3uae3@gmail.com\",\"card_expiry_date\":\"12-2021\",\"card_cvn\":\"123\",\"Amount\":\"0.0\",\"card_type\":\"001\"}";

  // final static String jsondata2 =
  // "{\"card_number\":\"4111111111111111\",\"bill_to_email\":\"c4d3uae3@gmail.com\",\"card_expiry_date\":\"12-2021\",\"card_cvn\":\"123\",\"card_type\":\"001\"}";


  /**
   * Converting refundJson String to RefundVO object
   * 
   * @param refundJson
   * @return
   */
  public static RootData convertToPOJO(String refundJson) {

    RootData pojo = new RootData();
    ObjectMapper mapper = new ObjectMapper();
    try {
      ObjectReader reader = mapper.readerFor(RootData.class).withRootName("rootData");
      // LocationCustomDataAttributes actual = reader.readValue(json);
      // ObjectMapper mapper = new ObjectMapper().setVisibility(JsonMethod.FIELD, Visibility.ANY);
      // mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      refundJson = refundJson.replaceAll("Amount", "amount");
      // System.out.println("refundJson:" + refundJson);
      pojo = mapper.readValue(refundJson, RootData.class);
      System.out.println("pojo:" + pojo);
    } catch (Exception e) {
      System.err.println("JSON to Java Object Conversion Error:" + e.getMessage());
      return null;
    }
    return pojo;
  }

  public static RootData2 convertToPOJO2(String refundJson) {

    RootData2 pojo = new RootData2();
    ObjectMapper mapper = new ObjectMapper();
    try {
      pojo = mapper.readValue(refundJson, RootData2.class);
      System.out.println("pojo:" + pojo);
    } catch (Exception e) {
      System.err.println("JSON to Java Object Conversion Error:" + e.getMessage());
      return null;
    }
    return pojo;
  }

  public static void main(String[] args) {
    convertToPOJO(jsondata);
    // convertToPOJO2(jsondata2);
  }

  class AttributesWrapper {
    private RootData rootData;

    public RootData getRootData() {
      return rootData;
    }

    public void setRootData(RootData rootData) {
      this.rootData = rootData;
    }


  }
}
