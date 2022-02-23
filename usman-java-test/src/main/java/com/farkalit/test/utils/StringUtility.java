package com.farkalit.test.utils;

import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public class StringUtility {

  public static void main(String[] args) {

    System.out.println(maskCardNumber("411111111111111", null));
  }


  public static String maskCardNumber(final String cardNumber, String mask) {
    // final String s = cardNumber.replaceAll("\\D", "");
    Character chars = 'X';
    if (Objects.nonNull(mask) && !mask.isEmpty()) {
      chars = mask.charAt(0);
    }

    final int start = 6;
    final int end = cardNumber.length() - 4;
    final String overlay = StringUtils.repeat(chars, end - start);

    return StringUtils.overlay(cardNumber, overlay, start, end);
  }
}
