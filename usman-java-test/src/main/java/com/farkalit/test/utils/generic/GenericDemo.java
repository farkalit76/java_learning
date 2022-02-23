package com.farkalit.test.utils.generic;

public class GenericDemo {

  public static void main(String[] args) {

    Container<Integer> obj = new Container<>();
    obj.value = 90;
    // obj.show();

    Human<Student> human = new Human<>();
    human.value = new Student("STG1001");
    System.out.println(human.toString());
    human.value = new Student("Usman", 22, "STG1001");
    System.out.println(human.toString());

    // Human<Person> human2 = new Human();
    //
    // human2.value = new Person("Usman", 22);
    // System.out.println(human2.toString());
    Human<Employee> emp = new Human<>();
    emp.setValue(new Employee("XYZ", 30, "EMP-10019"));
    System.out.println(emp.toString());
    emp.show();
  }
}


class Human<T extends Person> {

  T value;

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public void show() {
    System.out.println(this.toString());
  }

  @Override
  public String toString() {
    return "Human [value=" + value + "]";
  }

}


abstract class Person {

  private String name;
  private int age;


  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }


  @Override
  public String toString() {
    return "Person [name=" + name + ", age=" + age + "]";
  }


}


class Student extends Person {

  private String rollNum;

  public Student(String rollNum) {
    super("Default", 20);
    this.rollNum = rollNum;
  }

  public Student(String name, int age, String rollNum) {
    super(name, age);
    this.rollNum = rollNum;
  }

  @Override
  public String toString() {
    return "-->Student [rollNum=" + rollNum + ", toString()=" + super.toString() + "]";
  }

}


class Employee extends Person {

  private String empNum;

  public Employee(String empNum) {
    super("Default Emp Num.", 20);
    this.empNum = empNum;
  }

  public Employee(String name, int age, String empNum) {
    super(name, age);
    this.empNum = empNum;
  }

  @Override
  public String toString() {
    return "-->Employee [empNum=" + empNum + ", toString()=" + super.toString() + "]";
  }

}


class Container<T extends Number> {

  T value;

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public void show() {
    System.out.println(value.getClass().getName());
  }

}
