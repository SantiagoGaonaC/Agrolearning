/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexcion;

import static Conexcion.HiloServidor.Agua;
import ConexionBD.CultivosCompara;
import ConexionBD.SQLUsuarios;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

public class Servidor extends Observable implements Runnable {

    SQLUsuarios modsql = new SQLUsuarios();

    public volatile ArrayList<Socket> Clientes;
    private double Agua;
    private int puerto;
    private ServerSocket servidor = null;
    private boolean x = true;
    public static Thread hilo = null;
    public static Socket sc;
    public static int Contador = 333;

    public double getAgua() {
        return Agua;
    }

    public static Thread getHilo() {
        return hilo;
    }

    public void setAgua(double Agua) {
        this.Agua = Agua;
    }

    public Servidor(int puerto) {
        this.puerto = puerto;
        this.Clientes = new ArrayList<Socket>();
        try {
            this.servidor = new ServerSocket(puerto);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Se acepto una nueva conexion");

    }

    public ArrayList<Socket> getintClien() {
        return Clientes;
    }

    @Override
    public void run() {

        double[] valores = {Agua};
        String[] Stringvalores = {"agua"};
        try {

            System.out.println("Servidor iniciado");
            while (true) {
                sc = servidor.accept();
                this.Clientes.add(sc);
                System.out.println("Cliente conectado");
                hilo = new Thread(new HiloServidor(sc, this.Clientes, this));
                System.out.println("servior clasee -> " + hilo.getName());
                hilo.start();

            }

        } catch (Exception e) {
        }
        Clientes.remove(sc);

        try {
            sc.close();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Cliente desconectado");
        System.out.println(" " + hilo.currentThread().getName());

    }

    public void nuClien(Socket soc, Thread tv) {

        for (int i = 0; i < Clientes.size(); i++) {
            if (Clientes.get(i) == null) {
                hilo.currentThread().interrupt();
                tv.currentThread().interrupt();
                Clientes.remove(i);
            }
        }
    }

    public void enviarInfo(String[] StringValores, double[] Varoles) throws IOException {

        for (Socket Sock : Clientes) {
            if (Sock.isConnected()) {
                DataOutputStream dos = new DataOutputStream(Sock.getOutputStream());
                for (int i = 0; i < StringValores.length; i++) {
                    dos.writeUTF(StringValores[i]);
                    dos.writeDouble(Varoles[i]);
                    dos.flush();

                }
            }
        }
    }

    public double RestarAgua(double agua) {
        double NuevaAgua = HiloServidor.Agua - agua;
        double eje = Math.random() * 501;
        eje = Math.round(eje * 100) / 100d;
        if (eje > 490) {
            NuevaAgua = NuevaAgua + 5000;
        }
        HiloServidor.Agua = NuevaAgua;

        double[] valores = {HiloServidor.Agua};
        String[] Stringvalores = {"agua"};
        try {
            enviarInfo(Stringvalores, valores);

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return NuevaAgua;
    }

    public void ActilizaPrecio(double agua, String nombre) {
        double g1 = 0;
        double g2 = 0;
        double g3 = 0;
        double g = 0;
        switch (nombre) {
            case "Zanahoria":
                g = HiloServidor.Zanahoria - agua;
                g1 = HiloServidor.Maiz + 0.32;
                g2 = HiloServidor.Papa + 0.25;
                g3 = HiloServidor.Tomate + 0.15;
                HiloServidor.Zanahoria = g;
                HiloServidor.Maiz = g1;
                HiloServidor.Papa = g2;
                HiloServidor.Tomate = g3;

                double[] valores = {g, g1, g2, g3};
                String[] Stringvalores = {nombre, "Maíz", "Papa", "Tomate"};
                try {
                    enviarInfo(Stringvalores, valores);

                } catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            case "Maíz":
                g = HiloServidor.Maiz - agua;
                g1 = HiloServidor.Zanahoria + 0.32;
                g2 = HiloServidor.Papa + 0.25;
                g3 = HiloServidor.Tomate + 0.15;
                HiloServidor.Maiz = g;
                HiloServidor.Zanahoria = g1;
                HiloServidor.Papa = g2;
                HiloServidor.Tomate = g3;

                double[] valores2 = {g, g1, g2, g3};
                String[] Stringvalores2 = {nombre, "Zanahoria", "Papa", "Tomate"};
                try {
                    enviarInfo(Stringvalores2, valores2);

                } catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            case "Papa":
                g = HiloServidor.Papa - agua;
                g1 = HiloServidor.Zanahoria + 0.32;
                g2 = HiloServidor.Maiz + 0.25;
                g3 = HiloServidor.Tomate + 0.15;
                HiloServidor.Papa = g;
                HiloServidor.Zanahoria = g1;
                HiloServidor.Maiz = g2;
                HiloServidor.Tomate = g3;

                double[] valores3 = {g, g1, g2, g3};
                String[] Stringvalores3 = {nombre, "Zanahoria", "Maíz", "Tomate"};
                try {
                    enviarInfo(Stringvalores3, valores3);

                } catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            case "Tomate":
                g = HiloServidor.Tomate - agua;
                g1 = HiloServidor.Zanahoria + 0.32;
                g2 = HiloServidor.Papa + 0.25;
                g3 = HiloServidor.Maiz + 0.15;
                HiloServidor.Tomate = g;
                HiloServidor.Zanahoria = g1;
                HiloServidor.Papa = g2;
                HiloServidor.Maiz = g3;

                double[] valores4 = {g, g1, g2, g3};
                String[] Stringvalores4 = {nombre, "Zanahoria", "Papa", "Maíz"};
                try {
                    enviarInfo(Stringvalores4, valores4);

                } catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            
        }

    }

    public void Recibir(Socket sc, DataInputStream in) throws IOException {
        in = new DataInputStream((sc.getInputStream()));
        double agua = in.readDouble();
        this.setChanged();
        this.notifyObservers(agua);
        this.clearChanged();

    }

    public void Recibiri(Socket sc, DataInputStream in) throws IOException {
        in = new DataInputStream((sc.getInputStream()));
        String agua = in.readUTF();
        this.setChanged();
        this.notifyObservers(agua);
        this.clearChanged();
    }

    public void RecibirDatosCliente(DataInputStream dis, Socket sc) throws IOException {
        dis = new DataInputStream((sc.getInputStream()));
        String Nombres;
        double valor;
        Nombres = dis.readUTF();
        this.setChanged();
        this.notifyObservers(Nombres);
        this.clearChanged();
        valor = dis.readDouble();
        this.setChanged();
        this.notifyObservers(valor);
        this.clearChanged();
    }

    public void Cerrar(int i) throws InterruptedException {
        System.out.println("---------->" + i);
        Clientes.remove(i);
    }

    public void Cerrar(String g) throws InterruptedException {
        System.out.println("El que vino " + g);
        /*
        for (int i = 0; i < Clientes.size(); i++) {
            if (Clientes.get(i) == g) {
                System.out.println("holi ->> "+Clientes.get(i).toString());
                Clientes.remove(i);
            }
            else{
                System.out.println("nada mijo");
            }
        }*/

    }

    public void Todo(Socket sc, DataInputStream in, double[] valores, String[] Stringvalores) throws IOException {
        enviarInfo(Stringvalores, valores);
        Recibir(sc, in);
    }

}
