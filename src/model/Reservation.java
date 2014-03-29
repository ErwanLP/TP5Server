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
public class Reservation implements Serializable {

    static private final long serialVersionUID = 192L;
    Piece pieceSelected;
    int nombreSelected;

    public Reservation(Piece pieceSelected, int nombreSelected) {
        this.pieceSelected = pieceSelected;
        this.nombreSelected = nombreSelected;

    }

    public String toString() {
        String s = "la piece : " + this.pieceSelected.nom + " est selectionnée avec une reservation de " + this.nombreSelected + " place(s)";
        return s;

    }

    //modifie la bdd pour une reservation
    public Boolean implementeReservation(Connection conn) throws SQLException {
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT * FROM piece WHERE id = " + this.pieceSelected.id;
        System.out.println(query);
        ResultSet res = state.executeQuery(query);
        res.first();
        //on regade si il est de la place
        if (res.getInt("placedispo") >= this.nombreSelected) {
            int newPlaceDispo = res.getInt("placedispo") - this.nombreSelected;
            res.updateInt("placedispo", newPlaceDispo);
            res.updateRow();
            res.close();
            state.close();
            return true;
        } else {
            System.out.println("pas assez de place");
            return false;
        }
    }
}
