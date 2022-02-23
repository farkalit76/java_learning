package com.farkalit.test.functional.programming.combinationpattern;

import static com.farkalit.test.functional.programming.combinationpattern.CustomerRegistrationValidator.ValidationResult.CUSTOMER_IS_NOT_ADULT;
import static com.farkalit.test.functional.programming.combinationpattern.CustomerRegistrationValidator.ValidationResult.EMAIL_NOT_VALID;
import static com.farkalit.test.functional.programming.combinationpattern.CustomerRegistrationValidator.ValidationResult.PHONE_NUMBER_NOT_VALID;
import static com.farkalit.test.functional.programming.combinationpattern.CustomerRegistrationValidator.ValidationResult.SUCCESS;
import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;
import com.farkalit.test.functional.programming.combinationpattern.CustomerRegistrationValidator.ValidationResult;

public interface CustomerRegistrationValidator extends Function<Customer, ValidationResult> {


  static CustomerRegistrationValidator isEmailValid() {
    return customer -> customer.getEmail().contains("@") && customer.getEmail().contains(".")
        ? SUCCESS
        : EMAIL_NOT_VALID;
  }

  static CustomerRegistrationValidator isPhoneValid() {
    return customer -> (customer.getPhoneNum().startsWith("97")
        && customer.getPhoneNum().length() == 10) ? SUCCESS
            : PHONE_NUMBER_NOT_VALID;
  }

  static CustomerRegistrationValidator isAnAdult() {
    return customer -> Period.between(customer.getDob(), LocalDate.now()).getYears() > 16
        ? SUCCESS
        : CUSTOMER_IS_NOT_ADULT;
  }


  default CustomerRegistrationValidator and(CustomerRegistrationValidator other) {
    return customer ->{
      ValidationResult result = this.apply(customer);
      return result.equals(SUCCESS) ? other.apply(customer) : result;
    };
  }

  enum ValidationResult {
    SUCCESS, EMAIL_NOT_VALID, PHONE_NUMBER_NOT_VALID, CUSTOMER_IS_NOT_ADULT
  }
}
