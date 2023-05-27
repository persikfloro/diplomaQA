package ru.netology.data;

import lombok.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.DriverManager;

public class SQL {
    private static QueryRunner runner = new QueryRunner();

    private SQL() {
    }

    public static String dbUrl = System.getProperty("dbUrl");
    public static String dbUser = System.getProperty("dbUser");
    public static String dbPassword = System.getProperty("dbPassword");

    @SneakyThrows
    public static String checkPaymentStatus() {
        var dataSQL = "SELECT status FROM payment_entity";
        try (var conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            var result = runner.query(conn, dataSQL, new ScalarHandler<String>());
            return result;
        }
    }


    @SneakyThrows
    public static String checkCreditStatus() {
        var dataSQL = "SELECT status FROM credit_request_entity";
        try (var conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            var result = runner.query(conn, dataSQL, new ScalarHandler<String>());
            return result;
        }
    }

    @SneakyThrows
    public static void clear() {
        var runner = new QueryRunner();
        var conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        runner.update(conn, "DELETE FROM credit_request_entity");
        runner.update(conn, "DELETE FROM payment_entity");
    }

}