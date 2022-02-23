package com.farkalit.test.functional.programming;

import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionMain {

  public static void main(String[] args) {
    int increment = incrementByOne(1);
    System.out.println("increment:" + increment);
    int multiply = incrementByOneAndMultiply(2, 10);
    System.out.println("add one and multiply:" + multiply);


    int incr2 = incrementByOneFunction.apply(5);
    System.out.println(incr2);

    int multiply10 = multiplyBy10Function.apply(incr2);
    System.out.println(multiply10);
    
    Function<Integer, Integer> andThen = incrementByOneFunction.andThen(multiplyBy10Function);
    System.out.println(andThen.apply(5));
    

    // BiFunction example
    Integer apply = addOneThenMultiplyBiFunction.apply(4, 100);
    System.out.println(apply);

  }


  static Function<Integer, Integer> incrementByOneFunction = number -> number + 1;

  /**
   * Function: A function that accept one parameter and produces one result.
   */
  static Function<Integer, Integer> multiplyBy10Function = number -> number * 10;

  /**
   * BiFunction: A function that accept TWO parameters and produces one result.
   */
  static BiFunction<Integer, Integer, Integer> addOneThenMultiplyBiFunction =
      (number, multiply) -> (number + 1) * multiply;

  private static int incrementByOneAndMultiply(int number, int multiply) {
    return (number + 1) * multiply;
  }

  private static int incrementByOne(int number) {
    return number + 1;
  }


}
