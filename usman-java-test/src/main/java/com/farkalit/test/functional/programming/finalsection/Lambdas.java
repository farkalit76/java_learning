package com.farkalit.test.functional.programming.finalsection;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Lambdas {

  public static void main(String[] args) {

    // Function<String, String> nameUpper = name -> name.toUpperCase(); // String::toUpperCase

    Services service = new Services();

    System.out.println(service.NAME_UPPER_CASE.apply("Farkalit Usman"));


    System.out.println(nameAgeUpper.apply("Usman", 30));
    System.out.println(nameAgeUpper.apply("JAMIL", 10));
  }

  static BiFunction<String, Integer, String> nameAgeUpper = (name, age) -> {
    // logic here
    System.out.println(age);
    if (name.isEmpty())
      throw new IllegalArgumentException("Null name.");

    if (age > 15)
      return name.toUpperCase();
    else
      return name.toLowerCase();
  };



}


class Services {

  public Function<String, String> NAME_UPPER_CASE = name -> name.toUpperCase();

}
