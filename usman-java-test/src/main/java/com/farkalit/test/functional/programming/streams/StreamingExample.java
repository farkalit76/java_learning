package com.farkalit.test.functional.programming.streams;

import static com.farkalit.test.functional.programming.streams.StreamingExample.Genders.FEMALE;
import static com.farkalit.test.functional.programming.streams.StreamingExample.Genders.MALE;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamingExample {


  public static void main(String[] args) {

    List<Persons> people = new ArrayList<>();
    people.add(new Persons("Usman", 25, MALE));
    people.add(new Persons("Akbari", 30, FEMALE));
    people.add(new Persons("Sabiha", 76, FEMALE));
    people.add(new Persons("Ahmad", 23, MALE));
    people.add(new Persons("Jamil", 50, MALE));
    people.add(new Persons("Dorsy", 45, FEMALE));

    // Imperative approach

    // Declarative approach

    // Filter
    System.out.println("Filter for FEMALE");
    people.stream().filter(p -> p.getGender().equals(FEMALE)).collect(Collectors.toList())
        .forEach(System.out::println);

    // Sort
    System.out.println("Sorting list");
    List<Persons> sorted =
        people.stream().sorted(Comparator.comparing(Persons::getAge)).collect(Collectors.toList());
    sorted.forEach(System.out::println);

    // All match
    boolean allMatch = people.stream().allMatch(x -> x.getAge() > 10);
    System.out.println("All Match:" + allMatch);

    // Any Match
    boolean anyMatch = people.stream().anyMatch(x -> x.getAge() > 70);
    System.out.println("Any Match: " + anyMatch);

    // None Match
    boolean noneMatch = people.stream().noneMatch(x -> x.getName().equals("Farkalit"));
    System.out.println("None Match: " + noneMatch);

    // MAX
    people.stream().max(Comparator.comparing(Persons::getAge)).ifPresent(x -> {
      System.out.println("Max age:" + x);
    });

    // MIN
    people.stream().min(Comparator.comparing(Persons::getAge)).ifPresent(x -> {
      System.out.println("Min age:" + x);
    });

    // Group
    Map<Genders, List<Persons>> groupByGender =
        people.stream().collect(Collectors.groupingBy(Persons::getGender));

    groupByGender.forEach( (gender, people1) ->  {
      System.out.println(gender);
      people1.forEach(System.out::println);
      System.out.println();
    });

    // Oldest Female name
    Optional<String> oldestFemaleName =
        people.stream().filter(person -> person.getGender().equals(FEMALE)) // get females
            .max(Comparator.comparing(Persons::getAge)) // get max age
            .map(Persons::getName); // get name
    oldestFemaleName.ifPresent(System.out::println);

  }

  static class Persons {
    private String name;
    private int age;
    private Genders gender;

    public Persons(String name, int age, Genders gender) {
      this.name = name;
      this.age = age;
      this.gender = gender;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }

    public Genders getGender() {
      return gender;
    }

    public void setGender(Genders gender) {
      this.gender = gender;
    }

    @Override
    public String toString() {
      return "Person [name=" + name + ", age=" + age + ", gender=" + gender + "]";
    }

  }

  public enum Genders {
    MALE, FEMALE, NOT_TO_SAY
  }

}
