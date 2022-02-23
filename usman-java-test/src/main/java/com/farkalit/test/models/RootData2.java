package com.farkalit.test.models;

// import javax.persistence.Column;

public class RootData2 {

  // @Column(name = "card_number")
  private String card_number;

  // @Column(name = "bill_to_email")
  private String bill_to_email;

  // @Column(name = "card_expiry_date")
  private String card_expiry_date;

  // @Column(name = "card_cvn")
  private String card_cvn;

  // @Column(name = "Amount")
  // private String Amount;

  // @Column(name = "card_type")
  private String card_type;

  public void setCard_number(String card_number) {
    this.card_number = card_number;
  }

  public String getCard_number() {
    return this.card_number;
  }

  public void setBill_to_email(String bill_to_email) {
    this.bill_to_email = bill_to_email;
  }

  public String getBill_to_email() {
    return this.bill_to_email;
  }

  public void setCard_expiry_date(String card_expiry_date) {
    this.card_expiry_date = card_expiry_date;
  }

  public String getCard_expiry_date() {
    return this.card_expiry_date;
  }

  public void setCard_cvn(String card_cvn) {
    this.card_cvn = card_cvn;
  }

  public String getCard_cvn() {
    return this.card_cvn;
  }

  // public void setAmount(String Amount) {
  // this.Amount = Amount;
  // }
  //
  // public String getAmount() {
  // return this.Amount;
  // }

  public void setCard_type(String card_type) {
    this.card_type = card_type;
  }

  public String getCard_type() {
    return this.card_type;
  }

  @Override
  public String toString() {
    return "RootData [card_number=" + card_number + ", bill_to_email=" + bill_to_email
        + ", card_expiry_date=" + card_expiry_date + ", card_cvn=" + card_cvn + ", card_type="
        + card_type + "]";
  }

  // public static RootData fill(JSONObject jsonobj) {
  // RootData entity = new RootData();
  // if (jsonobj.containsKey("card_number")) {
  // entity.setCard_number(jsonobj.getString("card_number"));
  // }
  // if (jsonobj.containsKey("bill_to_email")) {
  // entity.setBill_to_email(jsonobj.getString("bill_to_email"));
  // }
  // if (jsonobj.containsKey("card_expiry_date")) {
  // entity.setCard_expiry_date(jsonobj.getString("card_expiry_date"));
  // }
  // if (jsonobj.containsKey("card_cvn")) {
  // entity.setCard_cvn(jsonobj.getString("card_cvn"));
  // }
  // if (jsonobj.containsKey("Amount")) {
  // entity.setAmount(jsonobj.getString("Amount"));
  // }
  // if (jsonobj.containsKey("card_type")) {
  // entity.setCard_type(jsonobj.getString("card_type"));
  // }
  // return entity;
  // }
  //
  // public static List<RootData> fillList(JSONArray jsonarray) {
  // if (jsonarray == null || jsonarray.size() == 0)
  // return null;
  // List<RootData> olist = new ArrayList()<>();
  // for (int i = 0; i < jsonarray.size(); i++) {
  // olist.add(fill(jsonarray.getJSONObject(i)));
  // }
  // return olist;
  // }

}
