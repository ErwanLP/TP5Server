/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Erwan
 */
public class Piece implements Serializable{

    static private final long serialVersionUID = 192L;
    public int id;
    public String nom;
    public int placeInit;
    public int placeDispo;

    Piece(int id, String nom, int placeInit, int placeDispo) {
        this.id = id;
        this.nom = nom;
        this.placeInit = placeInit;
        this.placeDispo = placeDispo;
        

    }

}
