/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexcion;

import static Conexcion.Servidor.hilo;
import ConexionBD.CultivosCompara;
import ConexionBD.SQLUsuarios;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acer
 */
public class HiloServidor extends Observable implements Runnable {

    public volatile ArrayList<Socket> Clientes;
    public static double Agua = 100000;
    public static double Zanahoria = 2000;
    public static double Maiz = 700;
    public static double Tomate = 2400;
    public static double Papa = 1800;
    
    public Socket socket = null;
    DataInputStream in = null;
    DataOutputStream ou = null;
    Servidor s;
    SQLUsuarios modsql = new SQLUsuarios();

    public HiloServidor(Socket s) {
        this.socket = s;

    }

    public HiloServidor() {

    }

    public HiloServidor(Socket s, ArrayList<Socket> clientes, Servidor g) {
        this.socket = s;
        this.Clientes = clientes;
        this.s = g;

    }

    @Override
    public void run() {
        double[] valores1 = {Agua,Zanahoria,Maiz,Papa,Tomate};
        String[] Stringvalores1 = {"agua","Zanahoria","Maíz","Papa","Tomate"};

        try {

            int axu = 0;
            while (true) {
                double[] valores = {Agua};
                String[] Stringvalores = {"agua"};
                if (axu == 0) {
                    s.enviarInfo(Stringvalores1, valores1);
                }
                s.enviarInfo(Stringvalores, valores);
                //s.Recibir(socket, in);
                s.RecibirDatosCliente(in, socket);
                axu = 1;
            }

            //Imprimimos el mensaje recibido
        } catch (IOException e) {

        }
        Clientes.remove(socket);
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Cliente desconectado");
        System.out.println(" " + hilo.currentThread().getName());

    }

    public void enviarInfo(String[] StringValores, double[] Varoles) throws IOException {
        for (Socket Sock : Clientes) {
            DataOutputStream dos = new DataOutputStream(Sock.getOutputStream());
            for (int i = 0; i < StringValores.length; i++) {
                dos.writeUTF(StringValores[i]);
                dos.writeDouble(Varoles[i]);
            }
        }
    }

    public void enviarInfo2(int[] Varoles) throws IOException {
        for (Socket Sock : Clientes) {
            DataOutputStream dos = new DataOutputStream(Sock.getOutputStream());
            for (int i = 0; i < Varoles.length; i++) {
                dos.writeInt(Varoles[i]);
            }

        }

    }

    public double RestarAgua(double agua) {
        double NuevaAgua = Agua - agua;
        Agua = NuevaAgua;
        return NuevaAgua;
    }

    public void Recibir(Socket sc, DataInputStream in) {
        try {
            in = new DataInputStream((sc.getInputStream()));
            double agua = in.readDouble();
            this.setChanged();
            this.notifyObservers(agua);
            this.clearChanged();
        } catch (IOException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void jeje(Socket f) {
        s.Clientes.remove(f);
    }

    public void Todo(Socket sc, DataInputStream in, double[] valores, String[] Stringvalores) throws IOException {
        enviarInfo(Stringvalores, valores);
        Recibir(sc, in);
    }

}
