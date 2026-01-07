package co.istad.jdbc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {

    private static Connection conn;

    public static Connection getInstance(){
        return conn;
    }

    public static void init(){
        if (conn == null){
            try{
                Class.forName("org.postgresql.Driver");
                String url = "jdbc:postgresql://localhost:5432/a01_a2";
                String user = "postgres";
                String password = "qwer";
                 conn = DriverManager.getConnection(url,user,password);

            } catch (ClassNotFoundException e) {
                System.out.println("class not found: "  + e.getMessage());
            } catch (SQLException e) {
                System.out.println("SQL Error: "  + e.getMessage());
            }
        }else {
            System.out.println("connect already initialized ");
        }
    }
}
