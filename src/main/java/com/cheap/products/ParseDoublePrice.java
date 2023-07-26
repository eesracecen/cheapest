package com.cheap.products;

public class ParseDoublePrice {
    double price;
    public double parseDouble(String prices){
        String pricess = prices.replace(".", "");
        String priceD = pricess.replace(",", ".");
        String[] bolPrice = priceD.split(" ");
        try {
          price=Double.parseDouble(bolPrice[0]);
        } catch (NumberFormatException e) {

        }
        return price;
    }
}
