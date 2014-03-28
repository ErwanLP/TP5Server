/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import model.Reservation;

/**
 *
 * @author Erwan
 */
class Accepter_clients implements Runnable {

    private ServerSocket socketserver;
    private Socket socket;
    private Connection conn;
    private boolean isClosed = false;
    private Traitement traitement;

    public Accepter_clients(ServerSocket s, Connection conn, Traitement traitement) {
        this.socketserver = s;
        this.conn = conn;
        this.traitement = traitement;
    }

    public void run() {
        String message_distant;
        ObjectInputStream ois;
        InputStream is;
        OutputStream os;
        ObjectOutputStream oos;

        try {
            while (true) {
                socket = socketserver.accept(); // Un client se connecte on l'accepte
                is = socket.getInputStream();
                ois = new ObjectInputStream(is);
                System.out.println("reception user");
                User user = (User) ois.readObject();
//                ois.close();
//                is.close();
                if (user.action.equalsIgnoreCase("connexion")) {
                    if (user.verifBdd(conn)) {
                        System.out.println("cest bon");
                        os = socket.getOutputStream();
                        oos = new ObjectOutputStream(os);
                        oos.writeBoolean(true);
                        oos.flush();
                        oos.writeObject(traitement.ps);
                        Reservation reservation = (Reservation) ois.readObject();
                        System.out.println(reservation.toString());
                        Boolean test;
                        test = reservation.implementeReservation(conn);
                        traitement.postResa();
                        oos.writeBoolean(test);
                        oos.flush();
                        socket.close();
                    } else {
                        System.out.println("user non dans la base de donnér");
                        os = socket.getOutputStream();
                        oos = new ObjectOutputStream(os);
                        oos.writeBoolean(false);
                        socket.close();
                    }
                } else {
                    System.out.println("on entre l'user dans la bdd");
                    user.insertBdd(conn);
                    os = socket.getOutputStream();
                    oos = new ObjectOutputStream(os);
                    oos.writeBoolean(false);
                    socket.close();
                }
                if(isClosed){
                    socket.close();
                }

                
            }
            

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Accepter_clients.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Accepter_clients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
