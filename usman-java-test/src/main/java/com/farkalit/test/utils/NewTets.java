package com.farkalit.test.utils;

import java.util.UUID;

public class NewTets {

  public static void main(String[] args) {

    System.out.println(System.currentTimeMillis());
    System.out.println(UUID.randomUUID() + "_" + System.currentTimeMillis());

  }

  public static void testString() {

    int x, y, z;
    x = 9;
    y = 10;
    z = ++x + y++;
    System.out.println(z);
    System.out.println(x);
    System.out.println(y);

    String s1 = "yes";
    String s2 = "yes";

    String s3 = new String(s1);
    System.out.println(s1 == s2);
    System.out.println(s3 == s1);
  }
}
