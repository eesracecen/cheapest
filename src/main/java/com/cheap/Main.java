package com.cheap;
import com.cheap.hepsiburada.HepsiBurada;
import com.cheap.n11.N11;
import com.cheap.products.Product;
import com.cheap.trendyol.Trendyol;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        final String URL_TRENDYOL = "https://www.trendyol.com/sr?q=spor%20ayakkab%C4%B1&qt=spor%20ayakkab%C4%B1&st=spor%20ayakkab%C4%B1&os=1";
        Trendyol trendyol=new Trendyol(URL_TRENDYOL);
        trendyol.getProductList(URL_TRENDYOL);
        System.out.println("TRENDYOL");
     //hepsiburada da hata var imageler tabyolya fazla sayıda geliyor!!!
       final String URL_HEPSİBURADA="https://www.hepsiburada.com/spor-ayakkabilar-c-384551";
        HepsiBurada hepsiBurada=new HepsiBurada(URL_HEPSİBURADA);
        hepsiBurada.getProductList(URL_HEPSİBURADA);
        System.out.println("HEPSİBURADA");
        final String URL_N11="https://www.n11.com/spor-giyim-ve-ayakkabi/spor-ayakkabi";
        N11 n11=new N11(URL_N11);
        n11.getProductList(URL_N11);
        System.out.println("N11");

    }
}