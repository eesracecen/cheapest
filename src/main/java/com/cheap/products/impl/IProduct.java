package com.cheap.products.impl;

import com.cheap.products.Image;
import com.cheap.products.Product;
import org.jsoup.nodes.Element;
import java.util.List;


public interface IProduct {

  List<Product> getProductList(String source);

  List<String> getProductExtras(Element element);

  Image getProductImage(Element element);
}
