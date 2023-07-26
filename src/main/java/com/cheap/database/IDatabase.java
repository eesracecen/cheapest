package com.cheap.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface IDatabase {
     PreparedStatement getPreparedStatement() throws SQLException;
     PreparedStatement getPreparedStatementImage() throws SQLException;
     void closeConnection() ;
     Connection getConnection() throws SQLException;
}
