package com.cheap.products;

import java.util.List;

public class Product {

  private String name;
  private double price;
  private String ratingCount;
  private String discount;
  private Image image;
  private List extra;
  private String web;
  public Product(String name, double price, String ratingCount, String discount, Image image, List extra,String web) {
    this.name = name;
    this.price = price;
    this.ratingCount = ratingCount;
    this.discount = discount;
    this.image = image;
    this.extra=extra;
    this.web=web;
  }
public String getWeb(){return web;}
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getRatingCount() {
    return ratingCount;
  }

  public void setRatingCount(String ratingCount) {
    this.ratingCount = ratingCount;
  }

  public String getDiscount() {
    return discount;
  }

  public void setDiscount(String discount) {
    this.discount = discount;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }
  public List getExtra() {
    return extra;
  }
  public void setExtra(List extra) {
    this.extra = extra;
  }
}
