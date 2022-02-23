package com.farkalit.test.utils;

public class FineLargestNum {

  public static void main(String[] args) {

    int num[] = {1, 5, 6, 2};

    System.out.println("num.length:" + num.length);
    int temp;
    for (int i = 0; i < num.length; i++) {
      System.out.println(i);

      for (int j = i + 1; j < num.length; j++) {
        System.out.println(j);
        if (num[i] > num[j]) {
          temp = num[i];
          System.out.println("temp:" + temp);
          num[i] = num[j];
          System.out.println("num[i]:" + num[i]);
          num[j] = temp;
          System.out.println("num[j]:" + num[j]);
        }
      }
      printArr(num);
    }
    System.out.println("Largest:" + num[num.length - 1]);
    System.out.println("Smallet:" + num[0]);
  }

  private static void printArr(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + ",");
    }
    System.out.println();
  }
}
