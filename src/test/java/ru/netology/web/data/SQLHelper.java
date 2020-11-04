package ru.netology.web.data;

import lombok.val;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    public static String mySqlUrl = "jdbc:mysql://192.168.99.100:3306/app";
    public static String login = "app";
    public static String password = "pass";

    public static String getData(String getStatus, String column) throws SQLException {

        try (
                val conn = DriverManager.getConnection(mySqlUrl, login, password);
                val countStmt = conn.createStatement();
        ) {
            try (val rs = countStmt.executeQuery(getStatus)) {
                if (rs.next()) {
                    val data = rs.getString(column);
                    return data;
                }
            }
        }
        return null;
    }

    public static String getPaymentStatus() throws SQLException {
        val getStatus = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        val column = "status";
        return SQLHelper.getData(getStatus, column);
    }

    public static String getPaymentID() throws SQLException {
        val getStatus = "SELECT payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        val column = "payment_id";
        return SQLHelper.getData(getStatus, column);
    }

    public static String getTransactionID() throws SQLException {
        val getStatus = "SELECT transaction_id FROM payment_entity ORDER BY created DESC LIMIT 1";
        val column = "transaction_id";
        return SQLHelper.getData(getStatus, column);
    }

    public static void comparePaymentAndTransactionID() throws SQLException {
        getPaymentID().equals(getTransactionID());
    }
}
