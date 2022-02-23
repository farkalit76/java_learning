package com.farkalit.test.utils.generic;

import java.util.Arrays;
import java.util.List;

public class GenericClassExp {

  
  public static void main(String[] args) {
    // instance of Integer type
    Test<Integer> iObj = new Test<>(15);
    System.out.println(iObj.getObject());

    // instance of String type
    Test<String> sObj = new Test<>("Age");
    System.out.println(sObj.getObject());

    // Test2 example
    Test2<String, Integer> obj = new Test2<>("Your Age", 15);
    obj.print();

    List<String> arrs = Arrays.asList("Usman", "Jamil", "Hasan");
    Test2<String, List<String>> obj2 = new Test2<>("Name", arrs);
    obj2.print();

    List<PersonType> arrs2 = Arrays.asList(new PersonType(20, "Usman"), new PersonType(40, "Jamil"),
        new PersonType(50, "Hasan"));
    Test2<String, List<PersonType>> obj3 = new Test2<>("PersonType", arrs2);
    obj3.print();
  }

}


class Test<T> {

  // An object of type T is declared
  T obj;

  // constructor
  Test(T obj) {
    this.obj = obj;
  }

  public T getObject() {
    return this.obj;
  }
}


class Test2<T, U> {
  T obj1; // An object of type T
  U obj2; // An object of type U

  // constructor
  Test2(T obj1, U obj2) {
    this.obj1 = obj1;
    this.obj2 = obj2;
  }

  // To print objects of T and U
  public void print() {
    System.out.println(obj1);
    System.out.println(obj2);
  }
}


class PersonType {

  int age;
  String name;

  public PersonType() {}

  public PersonType(int age, String name) {
    this.age = age;
    this.name = name;
  }

  @Override
  public String toString() {
    return "PersonType [age=" + age + ", name=" + name + "]";
  }

}
