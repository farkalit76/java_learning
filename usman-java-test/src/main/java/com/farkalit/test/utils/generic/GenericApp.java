package com.farkalit.test.utils.generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class GenericApp {

  public static void main(String[] args) {

    ArrayList list = new ArrayList();
    list.add("hello");
    list.add(1110);
    list.add(false);

    // list.forEach(System.out::println);

    Set<String> s1 = new HashSet<>();
    s1.add("first");
    s1.add("second");
    s1.add("third");

    Set<String> s2 = new HashSet<>();
    s2.add("first");
    s2.add("first");
    s2.add("second");

    Set<String> unionSet = union(s1, s2);
    unionSet.stream().sorted();
    System.out.println(unionSet);
    List<String> strList = new ArrayList<>(unionSet);
    Collections.sort(strList);
    System.out.println(strList);

    Iterator<String> itr = unionSet.iterator();
    while (itr.hasNext()) {
      System.out.println("union :" + itr.next());
    }

    // intrersection
    Set<String> resultSet = intersection(s1, s2);

    Iterator<String> itr2 = resultSet.iterator();
    while (itr2.hasNext()) {
      System.out.println("intersection: " + itr2.next());
    }
  }

  public static <E> Set<E> union(Set<E> s1, Set<E> s2) {

    Set<E> result = new HashSet<>(s1);
    result.addAll(s2);
    return result;
  }

  public static <E> Set<E> intersection(Set<E> s1, Set<E> s2) {

    Set<E> result = new HashSet<>();
    Iterator<E> itr = s1.iterator();
    while (itr.hasNext()) {
      E val = itr.next();
      Iterator<E> itr2 = s2.iterator();
      while(itr2.hasNext()) {
        if (itr2.next().equals(val)) {
          result.add(val);
        }
      }
    }

    return result;
  }
}
