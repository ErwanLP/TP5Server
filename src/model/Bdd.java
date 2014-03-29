/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Erwan
 */
public class Bdd {

    //se conencter à la bdd
    
    static public Connection connexion() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        System.out.println("Driver OK ! ");
        String url = "jdbc:postgresql://localhost:5432/TP5";
        String user = "postgres";
        String passwd = "isep";
        Connection conn = DriverManager.getConnection(url, user, passwd);
        System.out.println("Connection effective !");
        return conn;
    }

}
