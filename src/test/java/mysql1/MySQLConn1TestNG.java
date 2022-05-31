package mysql1;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;

public class MySQLConn1TestNG {

    Connection conn;
    Statement stmt;

    @BeforeClass
    public void beforeClass() throws SQLException {
        // veritabani connection

        String url = "jdbc:mysql://localhost:3306/database3";
        String username = "root";
        String password = "";

        conn = DriverManager.getConnection(url, username, password);
        stmt = conn.createStatement();
    }

    @Test
    public void test1() throws SQLException {
        String sql = "SELECT * FROM personel " +
                "WHERE age>50 AND country LIKE 'u%' " +
                "ORDER By first_name";

        ResultSet rs = stmt.executeQuery(sql);

        rs.next();//ilk kayda gitti
        Assert.assertEquals(rs.getString(2), "Anselm");

        rs.next();
        Assert.assertEquals(rs.getString("country"), "Ukraine");
    }

    @Test
    public void test2() throws SQLException {
        String sql = "SELECT country, gender, COUNT(*) AS count FROM personel " +
                "GROUP BY country, gender" +
                " ORDER By country, gender";

        ResultSet rs = stmt.executeQuery(sql);

        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        System.out.println(columnCount);

        for (int i = 1; i <= columnCount; i++) {
            System.out.println(rsmd.getColumnLabel(i) + ", " + rsmd.getColumnTypeName(i));
        }
    }

    @Test
    public void test3() throws SQLException {
        String sql = "SELECT country, gender, COUNT(*) AS count FROM personel " +
                "GROUP BY country, gender" +
                " ORDER By country, gender";

        ResultSet rs = stmt.executeQuery(sql);

        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        System.out.println(columnCount);

        for (int i = 1; i <= columnCount; i++) {
            System.out.println(rsmd.getColumnLabel(i) + ", " +
                    rsmd.getColumnTypeName(i) + ", " +
                    rsmd.getColumnDisplaySize(i));
        }

        int col1 = rsmd.getColumnDisplaySize(1);
        int col2 = rsmd.getColumnDisplaySize(2);
        int col3 = rsmd.getColumnDisplaySize(3);

        String strFormat = "%-" + col1 + "s %-" + col2 + "s %-" + col3 + "s\n";

        String title1 = rsmd.getColumnLabel(1);
        String title2 = rsmd.getColumnLabel(2);
        String title3 = rsmd.getColumnLabel(3);

        System.out.printf(strFormat, title1, title2, title3);

        while (rs.next()) {
            System.out.printf(strFormat,
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3));
        }


    }

    @AfterClass
    public void afterClass() throws SQLException {
        //connection kapatilacak
        stmt.close();
        conn.close();
    }

}
