package com.cheap.products;

public class Image {

  private String alt;
  private String src;
  private String width;
  private String height;

  public Image(String alt, String src, String width, String height) {
    this.alt = alt;
    this.src = src;
    this.width = width;
    this.height = height;
  }

  public String getAlt() {
    return alt;
  }

  public void setAlt(String alt) {
    this.alt = alt;
  }

  public String getSrc() {
    return src;
  }

  public void setSrc(String src) {
    this.src = src;
  }

  public String getWidth() {
    return width;
  }

  public void setWidth(String width) {
    this.width = width;
  }

  public String getHeight() {
    return height;
  }

  public void setHeight(String height) {
    this.height = height;
  }
}
