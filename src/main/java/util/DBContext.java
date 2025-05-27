/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author ADMIN
 */


import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/capybook";
        String username = "sa";
        String password = "123456";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }
}
