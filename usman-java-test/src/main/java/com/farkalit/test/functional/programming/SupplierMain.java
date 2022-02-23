package com.farkalit.test.functional.programming;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class SupplierMain {

  public static void main(String[] args) {

    System.out.println(getDbConnUrl());

    // Functional approach with Supplier
    System.out.println("Using by Functional Supplier--- ");
    System.out.println(getDbConnUrlSupp.get());
    System.out.println(getDbConnUrlsSupp.get());
  }

  static String getDbConnUrl() {
    return "jdbc:mysql://localhost:5432/users";
  }

  /**
   * Supplier: Represents a supplier of results.
   */
  static Supplier<String> getDbConnUrlSupp = () -> "jdbc:mysql://localhost:5432/users";

  static Supplier<List<String>> getDbConnUrlsSupp =
      () -> Arrays.asList("jdbc:mysql://localhost:5432/users",
          "jdbc:mysql://localhost:5432/suppliers");
}
