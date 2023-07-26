package com.cheap.database;
import com.cheap.products.Product;
import java.sql.*;
// database sınıfı olustur tanımlamalrı oraya al
public class RecordManager {
    PreparedStatement preparedStatement;
    PostgreSqlDatabase database=new PostgreSqlDatabase();
    public void createRecord(Product product) {
        try {
            Object[] arrayExtra = product.getExtra().toArray();
            java.sql.Array sqlArrayExtra = database.getConnection().createArrayOf("varchar", arrayExtra);
            preparedStatement= database.getPreparedStatement();
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getRatingCount());
            preparedStatement.setString(4, product.getDiscount());
            preparedStatement.setArray(5, sqlArrayExtra);
            preparedStatement.setString(6, product.getWeb());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Veri başarıyla eklendi.");
            } else {
                System.out.println("Veri eklenirken bir hata oluştu.");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        database.closeConnection();
    }
    public void createRecordImage(String alt, String src, String height, String width) {
        try {
            preparedStatement= database.getPreparedStatementImage();
            preparedStatement.setString(1, alt);
            preparedStatement.setString(2, src);
            preparedStatement.setString(3, height);
            preparedStatement.setString(4, width);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Veri başarıyla eklendi.");
            } else {
                System.out.println("Veri eklenirken bir hata oluştu.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.closeConnection();
    }
}
