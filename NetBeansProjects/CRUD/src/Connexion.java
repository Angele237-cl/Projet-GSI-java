/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LGC
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Connexion {
private static final String URL =
"jdbc:mysql://localhost:3306/crudj?useSSL=false&serverTimezone=UTC";
private static final String USER = "root";
private static final String PASSWORD = "";
public static Connection getConnection() {
try {
Class.forName("com.mysql.cj.jdbc.Driver");
return DriverManager.getConnection(URL, USER, PASSWORD);
} catch (SQLException | ClassNotFoundException e) {
System.out.println("Erreur de connexion : " + e.getMessage());
return null;
}
}
}

  

