package com.farkalit.test.functional.programming.combinationpattern;

import java.time.LocalDate;

public class Customer {

  private String name;
  private String email;
  private String phoneNum;
  private LocalDate dob;


  public Customer(String name, String email, String phoneNum, LocalDate dob) {
    super();
    this.name = name;
    this.email = email;
    this.phoneNum = phoneNum;
    this.dob = dob;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNum() {
    return phoneNum;
  }

  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }

  public LocalDate getDob() {
    return dob;
  }

  public void setDob(LocalDate dob) {
    this.dob = dob;
  }

  @Override
  public String toString() {
    return "Customer [name=" + name + ", email=" + email + ", phoneNum=" + phoneNum + ", dob=" + dob
        + "]";
  }

}
