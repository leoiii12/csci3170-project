package com.exploration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Database {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:2312/db21", "Group21", "CSCI3170");
    }

    public static void executeSqls(List<String> sqls) throws SQLException {
        for (String sql : sqls) {
            Connection conn = Database.getConnection();

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();

            conn.close();
        }
    }

}
