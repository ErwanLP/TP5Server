/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5server;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JFrame;
import model.Bdd;
import model.Pieces;
import view.Fenetre;

/**
 *
 * @author Erwan
 */
public class Traitement {
    
    Pieces ps;
    Fenetre f;
    Connection conn;
    JFrame jf;

    Traitement() throws SQLException, ClassNotFoundException {
        conn = Bdd.connexion();
        ps = new Pieces(conn);
        jf = new JFrame();
        f = new Fenetre(jf);
        f.update(ps.listePieces);
        Server.main(conn,this);
    }

    void postResa() throws SQLException {
        ps = new Pieces(conn);
        f.update(ps.listePieces);
    }

}
