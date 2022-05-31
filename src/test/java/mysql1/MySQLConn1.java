package mysql1;

import java.sql.*;

public class MySQLConn1 {

    /*

    JDBC -->

        Veritabani yeri,
        kullanici,
        sifre

        ConnectionString

        lazimlar:
          Connection
          Statement
          ReocrdSet

     */

    public static void main(String[] args) {

        // Veri tabanina baglamank icin bir connection olusturmam lazim
        try {
            Connection conn = DriverManager.getConnection(
                    //"jdbc:mysql://127.0.0.1:3306/database3",
                     "jdbc:mysql://localhost:3306/database3",
                    "root",
                    ""
            );

            // Connection olustruduk
            Statement stmt = conn.createStatement();

            // istenen SQL sonucunu ResultSet'e aliyoruz.
            ResultSet rs = stmt.executeQuery("SELECT first_name, last_name, age FROM personel LIMIT 10");

            while (rs.next()) {
                String name = rs.getString(1);
                String lastname = rs.getString(2);
                int age = rs.getInt("age");
                //   rs.getString("age")
                System.out.println(
                       name+ ", " + lastname + ", " + age);
            }

            // rs'de index birden baslar
            // Veritabani baglantisini kapatiyoruz.
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
