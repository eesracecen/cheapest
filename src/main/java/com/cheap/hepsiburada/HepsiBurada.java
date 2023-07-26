package com.cheap.hepsiburada;
import com.cheap.products.ParseDoublePrice;
import com.cheap.products.Extra;
import com.cheap.products.Image;
import com.cheap.products.Product;
import com.cheap.products.impl.IProduct;
import com.cheap.recordManager.RecordManagerImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.cheap.database.RecordManager;
import java.util.ArrayList;
import java.util.List;


public class HepsiBurada implements IProduct {
  final static String PRODUCTS_URL = "li.productListContent-zAP0Y5msy8OHn5z7T_K_";
  final static String NAME = "h3[data-test-id=product-card-name]";
  final static String PRICE = "div[data-test-id=price-current-price]";
  final static String RATING_COUNT = "div[data-test-id=review]";
  final static String DISCOUNT = "div[price.old.product-old-price]";
  final static String EXTRA = "div[data-test-id=product-image-footer-label]";
  final static String PRODUCT_BUY_DISCOUNT = "div[data-test-id=campaign]";
  final static String COLLECTABLE_COUPON= "div[data-test-id=product-image-header]";

  private String dataSourceUrl;

  public HepsiBurada(String dataSourceUrl) {
    this.dataSourceUrl = dataSourceUrl;
  }

  public String getDataSourceUrl() {
    return dataSourceUrl;
  }

  public void setDataSourceUrl(String dataSourceUrl) {
    this.dataSourceUrl = dataSourceUrl;
  }

  @Override
  public List<Product> getProductList(String source) {
    List<Product> arrayList = new ArrayList<>();
    try {
      Document doc = Jsoup.connect(source).get();
      Elements products = doc.select(PRODUCTS_URL);
      for (Element product : products) {
        String name = product.select(NAME).text();
        String priceS = product.select(PRICE).text();
        String ratingCount = product.select(RATING_COUNT).text();
        String discount = product.select(DISCOUNT).text();
        Image productImage = this.getProductImage(product);
        List productExtra=this.getProductExtras(product);
        RecordManager recordManager=new RecordManager();
        ParseDoublePrice priceD=new ParseDoublePrice();
        double price= priceD.parseDouble(priceS);
        Product product1 = new Product(name, price, ratingCount, discount, productImage, productExtra,"hepsiburada");
        arrayList.add(product1);
        recordManager.createRecord(product1);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return arrayList;
  }

  @Override
  public List<String> getProductExtras(Element element) {
    List<String> extraList = new ArrayList<>();
    String extra = element.select(EXTRA).text();
    String productBuyDiscount = element.select(PRODUCT_BUY_DISCOUNT).text();
    String collectableCoupon = element.select(COLLECTABLE_COUPON).text();
    extraList.add(extra);
    extraList.add(productBuyDiscount);
    extraList.add(collectableCoupon);
    Extra list=new Extra(extraList);
    return list.getAttributeList();
  }

  @Override
  public Image getProductImage(Element element) {
    Elements image = element.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
    RecordManager recordManager=new RecordManager();
    recordManager.createRecordImage(image.attr("alt"), image.attr("src"), image.attr("width"), image.attr("height"));
    return new Image(image.attr("alt"), image.attr("src"), image.attr("width"), image.attr("height"));
  }
}

