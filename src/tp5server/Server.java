/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tp5server;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Connection;

/**
 *
 * @author Erwan
 */
public class Server {
    	public static void main(Connection conn, Traitement traitement){
		
		ServerSocket socket;
		try {
		socket = new ServerSocket(2009);
		Thread t = new Thread((Runnable) new Accepter_clients(socket, conn, traitement));
		t.start();
		System.out.println("Server OK !");
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
    
}
