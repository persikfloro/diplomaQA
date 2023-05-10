package ru.netology.data;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import ru.netology.pages.BuyInCredit;
import ru.netology.pages.PayByCard;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SQL {
    private static QueryRunner runner = new QueryRunner();

    private static String url = System.getProperty("db.url");
    private static String user = System.getProperty("db.user");
    private static String password = System.getProperty("db.password");
    public static void clear() throws SQLException {
        var runner = new QueryRunner();
        var conn = DriverManager.getConnection(url, user, password);
        runner.update(conn, "DELETE FROM credit_request_entity;");
        runner.update(conn, "DELETE FROM payment_entity;");
        runner.update(conn, "DELETE FROM order_entity;");
    }

    public static void checkPaymentStatus(Status status) throws SQLException {
        var runner = new QueryRunner();
        var conn = DriverManager.getConnection(url, user, password);
        var paymentDataSQL = "SELECT status FROM payment_entity;";
        var payment = runner.query(conn, paymentDataSQL, new BeanHandler<>(PayByCard.class));
        assertEquals(status, payment.status);
    }

    public static void checkCreditStatus(Status status) throws SQLException {
        var runner = new QueryRunner();
        var conn = DriverManager.getConnection(url, user, password);
        var creditDataSQL = "SELECT status FROM credit_request_entity;";
        var credit = runner.query(conn, creditDataSQL, new BeanHandler<>(BuyInCredit.class));
        assertEquals(status, credit.status);
    }


}
