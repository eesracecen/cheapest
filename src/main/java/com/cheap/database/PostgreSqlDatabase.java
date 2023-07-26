package com.cheap.database;
import java.sql.*;
public class PostgreSqlDatabase implements IDatabase{
    PreparedStatement preparedStatement;
    private final String DB_URL = "jdbc:postgresql://localhost:5432/cheapest";
    private final String DB_USER = "postgres";
    private final String DB_PASSWORD = "232322";
    private Connection connection = null;
    public PreparedStatement getPreparedStatement() throws SQLException {
        String insertionQuery = "INSERT INTO product(name, price, ratingcount,discount, extra, web) VALUES (?,?,?,?,?,?)";
        preparedStatement = getConnection().prepareStatement(insertionQuery);
        return preparedStatement ;
    }
   public PreparedStatement getPreparedStatementImage() throws SQLException {
       String insertionQuery = "INSERT INTO image(alt, src, height, width) VALUES (?,?,?,?)";
       preparedStatement = getConnection().prepareStatement(insertionQuery);
       return preparedStatement;
   }
   public void closeConnection(){
       try {
           preparedStatement.close();
           connection.close();}
       catch (Exception e) {}
   }
   public Connection getConnection() throws SQLException{
    connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    return connection;
   }

}
