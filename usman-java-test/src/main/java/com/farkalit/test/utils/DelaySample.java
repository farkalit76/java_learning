package com.farkalit.test.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DelaySample {

  public static void main(String[] args) {
    // DelayUtil d = new DelayUtil();
    System.out.println("started:" + new Date());
    // d.delay(1000);
    // System.out.println("one second after:" + new Date());
    // d.delay(10, TimeUnit.SECONDS);
    // System.out.println("10 seconds after:" + new Date());
    for (int i = 0; i < 10; i++) {
      String result = getResult(i);
      if (!result.equals("SUCCESS")) {
        System.out.println("Result not success for [i]= " + i);
        DelayUtil.delay(1, TimeUnit.SECONDS);
      } else {
        System.out.println("Result success, quit...");
        break;
      }
    }
    System.out.println("EndTime:" + new Date());
  }

  public static String getResult(int n) {
    System.out.println("Check the value");
    if (n == 10) {
      return "SUCCESS";
    } else {
      return "FAILED";
    }
  }
}
