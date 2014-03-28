/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Piece;

/**
 *
 * @author Erwan
 */
public class Fenetre {

    PanelPieces contentPieces;

    public Fenetre(JFrame jf) {
        final int hauteurFenetre = 200;
        final int largeurFenetre = 300;
        jf.setTitle("Server TP5 By Erwan Le Poder");
        jf.setSize(largeurFenetre, hauteurFenetre);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.getContentPane().setLayout(new BorderLayout());
        JLabel labelTitre = new JLabel(" Piece Disponible :");
        jf.getContentPane().add(labelTitre, BorderLayout.NORTH);
        contentPieces = new PanelPieces();
        jf.getContentPane().add(contentPieces, BorderLayout.CENTER);
        jf.setVisible(true);
    }

    public void update(ArrayList<Piece> listePieces) {
        
        contentPieces.listePieces = listePieces;
        contentPieces.repaint();
    }

}
