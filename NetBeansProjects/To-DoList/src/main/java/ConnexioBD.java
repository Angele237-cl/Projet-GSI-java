

package Todolist;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LGC
 */
public class ConnexioBD {
     private static final String URL = "jdbc:mysql://localhost:3306/devoir";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnexion() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion réussie !");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
        return conn;
    }
 
}
