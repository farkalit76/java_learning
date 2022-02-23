package com.farkalit.test.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GetTimeInSeconds {

  public static void main(String[] args) throws ParseException {
    System.out.println(System.currentTimeMillis());

    long epoch =
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse("2021-07-09 13:20:49.160")
            .getTime();
    System.out.println("epoch-:" + epoch);

    long epoch2 =
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse("2021-07-09 14:21:49.167")
            .getTime();
    System.out.println("epoch2:" + epoch);
  }

}
