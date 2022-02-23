package com.farkalit.test.functional.programming.streams;

import static com.farkalit.test.functional.programming.streams.StreamMain.Gender.FEMALE;
import static com.farkalit.test.functional.programming.streams.StreamMain.Gender.MALE;
import static com.farkalit.test.functional.programming.streams.StreamMain.Gender.NOT_TO_SAY;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamMain {


  public static void main(String[] args) {

    List<Person> people = new ArrayList<>();
    people.add(new Person("Usman", 25, MALE));
    people.add(new Person("Akbari", 30, FEMALE));
    people.add(new Person("Ahmad", 23, MALE));
    people.add(new Person("Jamil", 50, MALE));
    people.add(new Person("Doe", 45, FEMALE));
    // people.add(new Person("Bob", NOT_TO_SAY));

    // Function<Person, String> personStrFunction = person -> person.name;
    // ToIntFunction<String> strLengthFunction = str -> str.length();
    // IntConsumer printInt = x -> System.out.println(x);

    // people.stream().map(person -> person.name).mapToInt(str -> str.length())
    // .forEach(System.out::println);


    Predicate<Person> predicate = person -> FEMALE.equals(person.gender);

    boolean allFemales = people.stream().allMatch(predicate);
    System.out.println("allFemales:" + allFemales);

    boolean anyFemales = people.stream().anyMatch(predicate);
    System.out.println("anyFemales:" + anyFemales);

    Predicate<Person> predicate2 = person -> NOT_TO_SAY.equals(person.gender);
    boolean noneMatch = people.stream().noneMatch(predicate2);
    System.out.println("noneMatch:" + noneMatch);

    // people.stream().sorted().forEach(System.out::println);
    List<Integer> list = Arrays.asList(10, 23, -4, 0, 18);
    List<Integer> sortedList = list.stream().sorted().collect(Collectors.toList());
    System.out.println(list);
    System.out.println(sortedList);

    List<String> list2 = Arrays.asList("John", "Mark", "Robert", "Lucas", "Brandon");
    List<String> sortedList2 = list2.stream().sorted().collect(Collectors.toList());
    System.out.println(sortedList2);

    List<Person> personList = people.stream().sorted(Comparator.comparingInt(Person::getAge))
        .collect(Collectors.toList());

    personList.forEach(System.out::println);

  }

  static class Person {
    private String name;
    private int age;
    private Gender gender;

    public Person(String name, int age, Gender gender) {
      this.name = name;
      this.age = age;
      this.gender = gender;
    }

    public int getAge() {
      return this.age;
    }

    @Override
    public String toString() {
      return "Person [name=" + name + ", age=" + age + ", gender=" + gender + "]";
    }

  }

  public enum Gender {
    MALE, FEMALE, NOT_TO_SAY
  }
}
