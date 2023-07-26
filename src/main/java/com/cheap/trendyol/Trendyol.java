package com.cheap.trendyol;

import com.cheap.database.RecordManager;
import com.cheap.products.ParseDoublePrice;
import com.cheap.recordManager.RecordManagerImpl;
import com.cheap.products.Image;
import com.cheap.products.Product;
import com.cheap.products.impl.IProduct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Trendyol implements IProduct {
    final static String PRODUCTS_URL="div.p-card-wrppr.with-campaign-view";
    final static String NAME =".prdct-desc-cntnr";
    final static String PRICE=".prc-box-dscntd";
    final static String RATING_COUNT=".ratingCount";
    final static String DISCOUNT=".prc-box-orgnl";
    final static String FREE_CARGO=".product-stamp-container.freeCargo";
    final static String RUSH_DELIVERY=".product-stamp-container.rushDelivery";
    final static String SAME_DAY_SHIPPING=".product-stamp-container.sameDayShipping";
    final static String COLLECTABLE_COUPON=".badge-wrapper.COLLECTABLE_COUPON";
    final static String TAKE_MORE_PAY_LESS=".badge-wrapper.TAKE_MORE_PAY_LESS";
    final static  String COUPON_DİSCOUNT=".badge-wrapper.COUPON_DISCOUNT";
    private String dataSourceUrl;

    public Trendyol(String dataSourceUrl) {
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
                Product product1 = new Product(name, price , ratingCount, discount, productImage, productExtra,"trendyol");
                arrayList.add(product1);
                recordManager.createRecord(product1);
                //database.closeConnection();
               // recordManager.close();
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
        String freeCargo = element.select(FREE_CARGO).text();
        String unitPrice = element.select(RUSH_DELIVERY).text();
        String sameDayShıppıng = element.select(SAME_DAY_SHIPPING).text();
        String collectableCoupon = element.select(COLLECTABLE_COUPON).text();
        String takeMorePayLess = element.select(TAKE_MORE_PAY_LESS).text();
        String couponDiscount = element.select(COUPON_DİSCOUNT).text();
        extraList.add(freeCargo);
        extraList.add(unitPrice);
        extraList.add(sameDayShıppıng);
        extraList.add(collectableCoupon);
        extraList.add(takeMorePayLess);
        extraList.add(couponDiscount);
        return extraList;
    }

    @Override
    public Image getProductImage(Element element) {
        Elements image = element.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        RecordManager recordManager=new RecordManager();
        recordManager.createRecordImage(image.attr("alt"), image.attr("src"), image.attr("width"), image.attr("height"));
       // recordManager.close();
        return new Image(image.attr("alt"), image.attr("src"), image.attr("width"), image.attr("height"));

    }

}
