package com.farkalit.test.functional.programming.combinationpattern;

import java.time.LocalDate;
import com.farkalit.test.functional.programming.combinationpattern.CustomerRegistrationValidator.ValidationResult;

public class CPMain {

  public static void main(String[] args) {

    Customer usman =
        new Customer("usman", "usman@gmail.com", "9711111110", LocalDate.of(2000, 10, 15));

    System.out.println(new CustomerValidatorService().isValid(usman));
    
    
    // Combinator pattern of Java 8
    ValidationResult result = CustomerRegistrationValidator.isEmailValid()
        .and(CustomerRegistrationValidator.isPhoneValid())
        .and(CustomerRegistrationValidator.isAnAdult()).apply(usman);

    System.out.println(result);

    if (result != ValidationResult.SUCCESS) {
      throw new IllegalStateException("Validation Error :" + result);
    } else {
      System.out.println("All validation success.");
    }

  }
}
