package com.farkalit.test.functional.programming.optional;

import java.util.Optional;
import java.util.function.Supplier;

public class OptionalMain {

  public static void main(String[] args) {

    String value = Optional.ofNullable("test").orElse("Default value");
    System.out.println(value);

    String test = "Employee@mafcarrefour.com";

    Supplier<IllegalStateException> exceptionSupplier =
        () -> new IllegalStateException("Value Exception");
    String value2 =
        Optional.ofNullable(test).orElseThrow(exceptionSupplier);
    System.out.println(value2);

    Optional.ofNullable(test).ifPresent(email -> 
    System.out.println("Sending email to :" + email));

  }
}
