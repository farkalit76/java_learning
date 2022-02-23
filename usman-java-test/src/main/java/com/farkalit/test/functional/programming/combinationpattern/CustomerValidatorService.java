package com.farkalit.test.functional.programming.combinationpattern;

import java.time.LocalDate;
import java.time.Period;

public class CustomerValidatorService {


  public boolean isValid(Customer customer) {
    return isEmailValid(customer.getEmail()) && isPhoneValid(customer.getPhoneNum())
        && isAdult(customer.getDob());
  }

  private boolean isEmailValid(String email) {
    return email.contains("@") && email.contains(".");
  }

  private boolean isPhoneValid(String phone) {
    return phone.startsWith("97") && phone.length() == 10;
  }

  private boolean isAdult(LocalDate dob) {
    return Period.between(dob, LocalDate.now()).getYears() > 16;
  }
}
