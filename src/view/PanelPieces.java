/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import model.Piece;

/**
 *
 * @author Erwan
 */
class PanelPieces extends JPanel {

    public ArrayList<Piece> listePieces = new ArrayList<Piece>();
    Font font = new Font("Courier", Font.BOLD, 20);

    PanelPieces() {

    }

    public void paintComponent(Graphics g) {
//        g.setFont(font);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.BLACK);
        int y = 30;
        int x = 10;
        String s;
        for (int i = 0; i < listePieces.size(); i++) {
            s = this.listePieces.get(i).id + " - " + this.listePieces.get(i).nom + " - " + this.listePieces.get(i).placeInit + " - " + this.listePieces.get(i).placeDispo;
            g.drawString(s, x, y);
            y += 20;
        }
    }

}
