/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Erwan
 */
public class User implements Serializable {

    static private final long serialVersionUID = 192L;
    public String prenom;
    public String nom;
    public String action;

    public User(String prenom, String nom, String action) {
        this.prenom = prenom;
        this.nom = nom;
        this.action = action;
    }

    public boolean verifBdd(Connection conn) throws SQLException {
        System.out.println("verifBdd");
//        Statement state = conn.createStatement();
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT * FROM user";
        query += " WHERE 'nom' = ?";
        query += " AND 'prenom' = ?";
        PreparedStatement prepare = conn.prepareStatement(query);
        prepare.setString(1, this.nom);
        prepare.setString(2, this.prenom);
        System.out.println(prepare.toString());
//        ResultSet result = state.executeQuery("SELECT * FROM user WHERE nom = " + this.nom + " AND prenom = " + this.prenom);
        ResultSet result = state.executeQuery(prepare.toString());
//        while (result.next()) {
            System.out.println(result.first());
//            System.out.println(result.getString("prenom"));
//            System.out.println(result.wasNull());
//        }
        return !result.wasNull();

    }

    public boolean insertBdd(Connection conn) throws SQLException {
        System.out.println("insertBdd");
//        Statement state = conn.createStatement();
        Statement st = conn.createStatement();
        st.executeUpdate("INSERT INTO " + '"' + "user" + '"' + "(prenom, nom) VALUES ('" + this.prenom + "','" + this.nom + "')");
        return true;
    }

}
