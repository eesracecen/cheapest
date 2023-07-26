package com.cheap.recordManager;

import com.cheap.products.Product;
public interface IRecordManager {

    void createRecord(Product product);
    void createRecordImage(String alt,String src,String height,String width);
}
