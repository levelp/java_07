import org.junit.Ignore;
import org.junit.Test;

import java.sql.*;

public class JDBCTest {
    @Ignore
    @Test
    public void testDatabase() throws SQLException {
        //Driver driver = new Driver();
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres", "123");
        // Создаем SQL оператор
        Statement select = con.createStatement();
        // Выполняем SQL запрос
        ResultSet rs = select.executeQuery("SELECT * FROM public.user");
        // Получаем результат
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " - " +
                    rs.getString("email"));
        }
    }
}
