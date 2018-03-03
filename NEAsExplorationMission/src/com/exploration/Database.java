package com.exploration;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:2312/db21", "Group21", "CSCI3170");
    }

    public static DSLContext getContext(Connection conn) {
        DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
        return create;
    }

    public static void executeSqls(List<String> sqls) throws SQLException {
        Connection conn = Database.getConnection();

        Statement stmt = conn.createStatement();
        for (String sql : sqls) {
            stmt.addBatch(sql);
        }
        stmt.executeBatch();
        stmt.close();

        conn.close();
    }

}
