/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexcion;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class HiloCliente extends Thread {

    private String Host;
    private int puerto;
    private DataInputStream dis;
    private cliente cli;
    public static volatile Socket sc;

    public HiloCliente(Socket s, cliente c) throws IOException {
        cli = c;
        sc = s;
    }

    public HiloCliente(int puerto, double AguaGastada, String Host) throws IOException {
        this.puerto = puerto;
        this.Host = Host;
        sc = new Socket(Host, puerto);
    }

    public Socket getSc() {
        return sc;
    }

    @Override
    public void run() {

        BufferedReader dis2 = null;

        try {
            
            while (true) {
             
                try {
                    dis = new DataInputStream(new BufferedInputStream(sc.getInputStream()));
                    cli.RecibirDatos2(dis);
                } catch (Exception e) {
                    System.out.println("Error while " + e );
                    e.printStackTrace();
                }

            }

            //out = new DataOutputStream(sc.getOutputStream());
            //out.write(AguaGastada);            
        } catch (Exception e) {
            String g = e.getMessage();
            if (g.equals("Connection reset")) {
                JOptionPane.showMessageDialog(null, "Error en el servirdor \n"
                        + "La aplicación será cerrada por serguridad \n"
                        + "Contactar a un administrador \n");
                System.exit(0);
            }
            System.out.println("Error en clinete " + e);
            
            e.printStackTrace();

        }
        /*
        try {
            sc.close();
        } catch (IOException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

}
