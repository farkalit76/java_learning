package com.farkalit.test.functional.programming;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class PredicateMain {

  public static void main(String[] args) {

    System.out.println("Without predicate");
    System.out.println(isPhoneValid("9712312312"));
    System.out.println(isPhoneValid("9812312312"));
    System.out.println(isPhoneValid("97312312"));

    // Functional approach with predicate
    System.out.println("With predicate ***");
    System.out.println(isPhoneValidPred.test("9712312312"));
    System.out.println(isPhoneValidPred.test("9812312312"));
    System.out.println(isPhoneValidPred.test("97312312"));

    // use and() & test()
    System.out.println("Is phone number is valid and contins 5 ="
        + isPhoneValidPred.and(isPhoneContains5Pred).test("9712312312"));
    System.out.println("Is phone number is valid and contins 5 ="
        + isPhoneValidPred.and(isPhoneContains5Pred).test("9712312315"));
    // use or() means one of them is true
    System.out.println("Is phone number is valid and contins 5 ="
        + isPhoneValidPred.or(isPhoneContains5Pred).test("9712312310"));

    // BiPredicate
    System.out.println("With BiPredicate ***");
    System.out.println("Is Phone size is valid: " + isPhoneValidSizeBiPred.test("9712345678", 10));
    System.out.println("Is Phone size is valid: " + isPhoneValidSizeBiPred.test("9712345678", 8));
  }



  static boolean isPhoneValid(String phoneNum) {
    return phoneNum.startsWith("97") && phoneNum.length() == 10;
  }


  static Predicate<String> isPhoneValidPred =
      phoneNum -> phoneNum.startsWith("97") && phoneNum.length() == 10;

  /**
   * Predicate: Boolean valued function with one parameter
   */
  static Predicate<String> isPhoneContains5Pred = phoneNum -> phoneNum.contains("5");

  /**
   * BiPredicate: Boolean valued function with TWO parameters.
   */
  static BiPredicate<String, Integer> isPhoneValidSizeBiPred =
      (phoneNum, size) -> phoneNum.length() == size;
}
