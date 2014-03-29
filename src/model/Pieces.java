/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Erwan
 */
public class Pieces implements Serializable
{

    static private final long serialVersionUID = 192L;
    public ArrayList<Piece> listePieces = new ArrayList<Piece>();

    public Pieces(Connection conn) throws SQLException {
        this.getAllPieces(conn);
//        this.afficheAllPiecesConsole();

    }

    //prend les piece dans la bdd
    public ResultSet getAllPieces(Connection conn) throws SQLException {
        try (Statement state = conn.createStatement(); ResultSet result = state.executeQuery("SELECT * FROM piece")) {
            while (result.next()) {
                Piece p = new Piece(result.getInt("id"), result.getString("nom"), result.getInt("placeinit"), result.getInt("placedispo"));
                this.listePieces.add(p);
            }
            result.close();
            state.close();
            return result;

        }
    }

    public void afficheAllPiecesConsole() throws SQLException {
        String s;
        for (int i = 0; i < this.listePieces.size(); i++) {
            s = listePieces.get(i).id + " - " + listePieces.get(i).nom + " - " + listePieces.get(i).placeInit + " - " + listePieces.get(i).placeDispo;
            System.out.println(s);

        }

    }

}
