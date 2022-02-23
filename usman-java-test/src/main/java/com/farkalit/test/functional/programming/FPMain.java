package com.farkalit.test.functional.programming;

import static com.farkalit.test.functional.programming.FPMain.Gender.FEMALE;
import static com.farkalit.test.functional.programming.FPMain.Gender.MALE;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FPMain {

  public static void main(String[] args) {
    System.out.println("Functional Programing");

    List<Person> people = new ArrayList<>();
    people.add(new Person("Usman", MALE));
    people.add(new Person("Akbari", FEMALE));
    people.add(new Person("Ahmad", MALE));
    people.add(new Person("Jamil", MALE));
    people.add(new Person("Doe", FEMALE));

    // List<Person> people2 = Arrays.asList(new Person("Doe", FEMALE), new Person("John", FEMALE));

    // Imperative approach to find only FEMALE
    ArrayList<Person> females = new ArrayList<>();
    for (Person person : people) {
      if (FEMALE.equals(person.gender)) {
        females.add(person);
      }
    }
    for (Person person : females) {
      System.out.println(person);
    }

    System.out.println("Declarative approach");
    // Declarative approach
    List<Person> females2 =
        people.stream().filter(p -> FEMALE.equals(p.gender)).collect(Collectors.toList());
    females2.forEach(System.out::println);

    // Declarative with Predicate approach
    Predicate<Person> predicate = p -> FEMALE.equals(p.gender);
    List<Person> females3 =
        people.stream().filter(predicate).collect(Collectors.toList());
    females3.forEach(System.out::println);

  }

  static class Person {
    private String name;
    private Gender gender;

    public Person(String name, Gender gender) {
      this.name = name;
      this.gender = gender;
    }

    @Override
    public String toString() {
      return "Person [name=" + name + ", gender=" + gender + "]";
    }

  }

  public enum Gender {
    MALE, FEMALE
  }
}
