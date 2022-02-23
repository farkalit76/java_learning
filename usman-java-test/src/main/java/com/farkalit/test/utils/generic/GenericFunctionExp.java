package com.farkalit.test.utils.generic;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GenericFunctionExp {



  // Driver method
  public static void main(String[] args) {
    // Calling generic method with Integer argument
    genericDisplay(11);

    // Calling generic method with String argument
    genericDisplay("GeeksForGeeks");

    // Calling generic method with double argument
    genericDisplay(1.0);

    String[] array = {"ONE", "TWO", "THREE"};
    System.out.println(fromArrayToList(array));

    givenArrayOfIntegers_thanListOfStringReturnedOK();
  }

  /**
   * A Generic method example
   * 
   * @param <T>
   * @param element
   */
  static <T> void genericDisplay(T element) {
    System.out.println(element.getClass().getName() + " = " + element);
    // return "OK";
  }

  /**
   * a generic method to convert an array to a list
   * 
   * The <T> in the method signature implies that the method will be dealing with generic type T.
   * This is needed even if the method is returning void.
   * 
   * @param <T>
   * @param a
   * @return
   */
  public static <T> List<T> fromArrayToList(T[] a) {
    return Arrays.stream(a).collect(Collectors.toList());
  }

  /**
   * As mentioned, the method can deal with more than one generic type. Where this is the case, we
   * must add all generic types to the method signature.
   * 
   * @param <T>
   * @param <G>
   * @param a
   * @param mapperFunction
   * @return
   */
  public static <T, G> List<G> fromArrayToList(T[] a, Function<T, G> mapperFunction) {
    System.out.println("Generice with Function");
    return Arrays.stream(a).map(mapperFunction).collect(Collectors.toList());
  }


  public static void givenArrayOfIntegers_thanListOfStringReturnedOK() {
    Integer[] intArray = {1, 2, 3, 4, 5};
    List<String> stringList = GenericFunctionExp.fromArrayToList(intArray, Object::toString);
    System.out.println(stringList);
  }

  public static String objectToString(Object obj) {
    return Objects.nonNull(obj) ? obj.toString() : null;
  }

  static Function<Integer, String> objectToStringFunction = number -> "" + number;

}
