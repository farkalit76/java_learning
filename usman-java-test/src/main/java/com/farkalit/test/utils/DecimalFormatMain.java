package com.farkalit.test.utils;

public class DecimalFormatMain {

  public static void main(String[] args) {
    // String d = "2343.5476";
    // double dd = Double.valueOf(d);
    // DecimalFormat df = new DecimalFormat("###.00");
    // System.out.println("Rounded double (DecimalFormat) : " + df.format(dd));

    String[] amount =
        {"2343.5476", "122.99", "1232.10", "120.01", "1111.00", "12345.", null, "", "555", "100"};
    for (String amt : amount) {
      System.out.println("Actual amount: " + amt);
      setDecimalAmountZero(amt);
    }
    System.out.println("\n\n\n");
    for (String amt : amount) {
      // System.out.println("refunded amt:" + amt);
      setDecimalRoundedUp(amt);
    }
  }

  private static void setDecimalAmountZero(String amount) {

    // String amount = paymentReq.getAmount();
    if (null != amount && amount.contains(".")) {
      String decamt = amount.substring(amount.indexOf("."), amount.length());
      amount = amount.replace(decamt, ".00");
      System.out.println("Rounded amount: " + amount);
      System.out.println();
    }
  }

  private static void setDecimalRoundedUp(String amount) {
    // String amount = refundReq.getAmount();
    System.out.println("refunded actual amount : " + amount);
    if (amount != null && amount.contains(".")) {
      String roundedUp = String.valueOf(Math.ceil(Double.parseDouble(amount)));
      System.out.println("amount after rounded UP: " + roundedUp);
      System.out.println();
    }
  }
}
