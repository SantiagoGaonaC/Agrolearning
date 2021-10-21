/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexcion;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import proyecto.VentanaCliente1;

public class cliente extends Observable implements Runnable {

    private String Host;
    private int puerto;
    private Thread aja;
    //private DataInputStream dis;
    private DataOutputStream out;
    public static volatile Socket sc;

    public cliente(int puerto, String Host) throws IOException {
        this.puerto = puerto;
        this.Host = Host;
        sc = new Socket(Host, puerto);
    }

    public cliente(int puerto, double AguaGastada, String Host) throws IOException {
        this.puerto = puerto;
        this.Host = Host;
        sc = new Socket(Host, puerto);

    }

    public Socket getSc() {
        return sc;
    }

    public Thread getAja() {
        return aja;
    }

    public void setAja(Thread aja) {
        this.aja = aja;
    }

    @Override
    public void run() {

        //BufferedReader dis2 = null;
        try {
            //dis = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream((sc.getOutputStream()));
//            //dis2 = new BufferedReader(new InputStreamReader(sc.getInputStream())) ;
//            String Nombres = null;
//            double valor = 0;
//            int conta = 0;
            Thread t12 = new HiloCliente(sc, this);
            t12.start();
//            while (true) {
//
//                System.out.println("Esperando respuesta");
//
//                //RecibirDatos(Nombres, valor,dis);
//                System.out.println(dis.available());
//                //RecibirDatos2(dis2, Nombres, valor,sc);
//            }

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

        }
        /*
        try {
            sc.close();
        } catch (IOException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    public void EnviarInfo3(String[] StringValores, double[] Varoles) throws IOException {
        DataOutputStream dos = new DataOutputStream(sc.getOutputStream());
        for (int i = 0; i < StringValores.length; i++) {
            dos.writeUTF(StringValores[i]);
            dos.writeDouble(Varoles[i]);
            sc.getOutputStream().flush();
            dos.flush();

        }

    }

    public void EnviarInfo2(String hi) throws IOException {
        DataOutputStream out;
        out = new DataOutputStream(sc.getOutputStream());
        out.writeUTF(hi);

    }

    public void RecibirDatos(DataInputStream dis2) {

        double valor;
        
        try {
            String Nombres;
            Nombres = dis2.readUTF();
            //Nombres = dis.read();
            this.setChanged();
            this.notifyObservers(Nombres);
            this.clearChanged();
        } catch (IOException e) {
            System.out.println("Error Nombre" + e);
            e.printStackTrace();
        }catch(Exception ec){
            System.out.println("Error Nombre" + ec);
            ec.printStackTrace();
        }
        try {
            valor = dis2.readDouble();
            this.setChanged();
            this.notifyObservers(valor);
            this.clearChanged();
        } catch (IOException e) {
            System.out.println("Error Valor" + e);
            e.printStackTrace();

        }catch(Exception ec){
            System.out.println("Error Valor" + ec);
            ec.printStackTrace();
        }
        

        

    }

    public void EnviarInfo(String g) throws IOException {
        //DataOutputStream out;
        //out = new DataOutputStream(sc.getOutputStream());
        out.writeUTF(g);
        out.flush();
        sc.getOutputStream().flush();
        System.out.println(" Envie " + g);

    }

    public void RecibirDatos2(DataInputStream dis) throws IOException {
        String Nombres;
        Nombres = dis.readUTF();
        System.out.println(Nombres);
        this.setChanged();
        this.setChanged();
       
        this.notifyObservers(Nombres);
        this.clearChanged();
        dis = null;


    }

}
