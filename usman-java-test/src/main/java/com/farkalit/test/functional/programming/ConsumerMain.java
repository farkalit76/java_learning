package com.farkalit.test.functional.programming;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ConsumerMain {

  public static void main(String[] args) {

    // Normal function call
    Customer usman = new Customer("Usman", "97213212312");
    greetCustomer(usman);

    greetCustomerBi(usman, false);

    // Consumer Functional approach
    greetCustomerByConsumer.accept(usman);

    greetCustomerByConsumerBi.accept(usman, false);

  }

  /**
   * Consumer: An operation that accepts a single input argument and returns no result
   */
  static Consumer<Customer> greetCustomerByConsumer = cust -> System.out
      .println("Hello " + cust.name + ", thanks for registering with phone:" + cust.phone);

  static BiConsumer<Customer, Boolean> greetCustomerByConsumerBi = (cust, show) -> System.out
      .println("Hello " + cust.name + ", thanks for registering with phone:"
          + (show ? cust.phone : "**********"));


  static void greetCustomer(Customer cust) {
    System.out.println("Hello " + cust.name + ", thanks for registering with phone:" + cust.phone);
  }

  static void greetCustomerBi(Customer cust, boolean show) {
    System.out.println("Hello " + cust.name + ", thanks for registering with phone:"
        + (show ? cust.phone : "**********"));
  }

  static class Customer {
    private String name;
    private String phone;

    public Customer(String name, String phone) {
      super();
      this.name = name;
      this.phone = phone;
    }

    @Override
    public String toString() {
      return "Customer [name=" + name + ", phone=" + phone + "]";
    }


  }
}
