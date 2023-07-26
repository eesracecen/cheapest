package com.cheap.n11;
import com.cheap.products.ParseDoublePrice;
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
public class N11 implements IProduct {
    final static String PRODUCTSURL=".pro";
    final static String NAME =".productName";
    final static String PRICE=".newPrice.cPoint.priceEventClick";
    final static String RATINGCOUNT=".ratingText";
    final static String DISCOUNT=".oldPrice.cPoint.priceEventClick";
    final static String FREECARGO=".cargoBadgeField";
    final static String UNITPRICE=".unitPrice";
    private String dataSourceUrl;

    public N11(String dataSourceUrl) {
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
            Elements products = doc.select(PRODUCTSURL);
            for (Element product : products) {
                String name = product.select(NAME).text();
                String priceS = product.select(PRICE).text();
                String ratingCount = product.select(RATINGCOUNT).text();
                String discount = product.select(DISCOUNT).text();
                Image productImage = this.getProductImage(product);
                List productExtra=this.getProductExtras(product);
                RecordManager recordManager=new RecordManager();
                ParseDoublePrice priceD=new ParseDoublePrice();
                double price= priceD.parseDouble(priceS);
                Product product1 = new Product(name, price, ratingCount, discount, productImage, productExtra,"N11");
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
        String freeCargo = element.select(FREECARGO).text();
        String unitPrice = element.select(UNITPRICE).text();
        extraList.add(freeCargo);
        extraList.add(unitPrice);
        return extraList;
    }
    @Override
    public Image getProductImage(Element element) {
        Elements image = element.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        RecordManager recordManager=new RecordManager();
        recordManager.createRecordImage(image.attr("alt"), image.attr("src"), image.attr("width"), image.attr("height"));
        return new Image(image.attr("alt"), image.attr("src"), image.attr("width"), image.attr("height"));
    }
}
