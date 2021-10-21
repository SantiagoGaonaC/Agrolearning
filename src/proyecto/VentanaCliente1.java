/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import Conexcion.HiloServidor;
import Conexcion.Servidor;
import Conexcion.cliente;
import ConexionBD.Cultivo;
import ConexionBD.CultivosCompara;
import ConexionBD.Estados;
import ConexionBD.SQLUsuarios;
import ConexionBD.Usuarios;
import java.awt.CardLayout;
import java.awt.Color;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observer;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import static proyecto.VentanaCliente1.c;
import static proyecto.VentanaCliente1.vp;

/**
 *
 * @author Acer
 */
public class VentanaCliente1 extends javax.swing.JFrame implements Observer {

    private String nombreProductoActual;
    public static Integer tiempo;
    public static Integer CantidadVecesVendidas;
    public static double PrecioFinal;
    public static Integer SemanasCultivo;
    public static Integer produccion;
    public static Integer NuepARTIDA;
    public static int Agua = 0;
    public static boolean AguaBoolean = false;
    public static boolean RegaPrime = false;
    public static boolean ServidorActivo = true;
    public static ArrayList<Boolean> primerRiego;
    public static ArrayList<Double> Precios;
    public static double[] precios1;
    public static ArrayList<Double> HistorialPrecios = new ArrayList<Double>();
    private Usuarios usu;
    public static cliente c;
    private Thread t;
    public static ArrayList<Cultivo> Cultivos;
    public static ArrayList<CultivosCompara> CultivosCompa;
    public static ArrayList<Estados> Estados;
    public static VentanaPlantar vp;
    public SQLUsuarios modSQL;
    DefaultListModel<String> modelo;
    Cultivo d;
    CardLayout vista;
    pnlGraficas pnlgraficas;
    pnlGraficas1 pnlgraficas1 = new pnlGraficas1();

    private void setColor(JPanel pane) {
        pane.setBackground(new Color(41, 57, 80));
    }

    private void resetColor(JPanel[] pane, JPanel[] indicators) {
        for (int i = 0; i < pane.length; i++) {
            pane[i].setBackground(new Color(0, 38, 28));

        }
        for (int i = 0; i < indicators.length; i++) {
            indicators[i].setBackground(new Color(0, 38, 28));
        }

    }

    public VentanaCliente1(Usuarios ElUsuario, ArrayList<Cultivo> Datos, SQLUsuarios modesql, Integer NumePar,
            ArrayList<CultivosCompara> datos2, ArrayList<Estados> eestados, int idOferta) throws IOException {

        try {
            setIconImage(new ImageIcon(getClass().getResource("/Recursos/holi.jpeg")).getImage());

            this.usu = ElUsuario;
            this.NuepARTIDA = NumePar;
            this.tiempo = usu.getSemanasJuga();
            this.modSQL = modesql;
            this.Cultivos = Datos;
            this.CultivosCompa = datos2;
            this.Estados = eestados;
            usu.setNumeroOFERTADEMANADA(idOferta);
            if (!modesql.ObtenerOfertaDe(usu)) {
                JOptionPane.showMessageDialog(this, "Error en la base de datos");
            }
            this.CantidadVecesVendidas = usu.getCantida();
            this.PrecioFinal = usu.getPrecio();
            if (this.CantidadVecesVendidas == 1) {
                pnlGraficas1.cANTIDAD.setText("Cultivos vendidos: " + this.CantidadVecesVendidas + " Lote");
            } else {
                pnlGraficas1.cANTIDAD.setText("Cultivos vendidos: " + this.CantidadVecesVendidas + " Lotes");
            }
            pnlGraficas1.Precio.setText("Capital Obtenido: " + this.PrecioFinal + " COP");
            this.produccion = 0;
            initComponents();
            setColor(btnInicio1);
            indInicio1.setBackground(new Color(39, 86, 43));
            resetColor(new JPanel[]{btngraficas2}, new JPanel[]{indgraficas1});
            this.setLocationRelativeTo(null);
            //c = new cliente(5000, "192.168.0.21");
            //c = new cliente(5000, "207.248.81.156");
            c = new cliente(5000, "18.215.144.23");
            c.addObserver(this);
            t = new Thread(c);
            c.setAja(t);
            t.start();
            this.LBNombreUsu.setText(this.usu.getNombres() + " " + this.usu.getApellidos());
            this.LBNombreFinca.setText(this.usu.getNombre_finca());
            this.LabelPartida.setText("Partida: " + this.NuepARTIDA.toString());
            this.jLABELsEmanas.setText("Semana: " + this.tiempo.toString());

            DefaultListModel<String> modelo1 = new DefaultListModel<String>();
            VentanaCliente1.ListasDeCultivos.setModel(modelo1);
            modelo = (DefaultListModel<String>) VentanaCliente1.ListasDeCultivos.getModel();
            ArrayList<Integer> indices = new ArrayList<Integer>();
            int conta = 0;
            for (Cultivo Dato : Cultivos) {
                if (Dato.getSemana() > 800) {
                    indices.add(conta);
                    conta++;
                } else {
                    if (Dato.getAltura() >= CultivosCompa.get(Dato.getIdCultivo() - 1).getAlturAmAXIam()) {
                        this.produccion = this.produccion + CultivosCompa.get(Dato.getIdCultivo() - 1).getCantidad();
                        indices.add(conta);
                        if (Cultivos.isEmpty()) {
                            break;
                        }
                        conta++;
                    } else {
                        modelo.addElement(Dato.getNombreCultivo());
                        Dato.setAguaRegistrada(false);
                        Dato.setBorrar(true);
                        conta++;
                    }

                }

            }
            Collections.reverse(indices);
            for (int indice : indices) {
                VentanaCliente1.Cultivos.remove(indice);
            }
            this.LabeProduc.setText(this.produccion.toString() + " X Kg");
            pnlGraficas1.producciones1312.setText("Cantidad de cultivos en total: " + this.produccion.toString() + " X Kg");
            this.setResizable(false);
            if (modelo1.isEmpty()) {
                this.TienesQueRegar.setVisible(false);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Servidor no encontrado \n"
                    + "Contactar a un administrador \n");
            modSQL.Salida(usu);
            this.ServidorActivo = false;
            this.dispose();
            System.exit(0);

        }
        this.precios1 = new double[CultivosCompa.size()];
        pnlgraficas = new pnlGraficas(Cultivos);

    }

    public VentanaCliente1() throws IOException {
        initComponents();
        this.setResizable(false);
        //VentanaCliente1.InventarioCliente.setEditable(false);
        this.setLocationRelativeTo(null);
        c = new cliente(5000, "54.161.133.42");
        c.addObserver(this);
        t = new Thread(c);
        t.start();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLABELsEmanas = new javax.swing.JLabel();
        LabelPartida = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btngraficas2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        indgraficas1 = new javax.swing.JPanel();
        btnInicio1 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        indInicio1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btngraficas = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        indgraficas = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        pnlPrincipal = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        LabelAgua = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        LabeProduc = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        LabelAltura = new javax.swing.JLabel();
        LabelSemanasCultivo = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lbliconfinca = new javax.swing.JLabel();
        lbliconuser = new javax.swing.JLabel();
        LBNombreUsu = new javax.swing.JLabel();
        LBNombreFinca = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        paneljej = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListasDeCultivos = new javax.swing.JList<>();
        jPanel8 = new javax.swing.JPanel();
        lblsembrar = new javax.swing.JLabel();
        lblregar = new javax.swing.JLabel();
        lbleliminar = new javax.swing.JLabel();
        lblsiguietne = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        LBNombreUsu1 = new javax.swing.JLabel();
        TienesQueRegar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Agro-Learning");
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(22, 128, 57));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/intAdmin/minimizarR.png"))); // NOI18N
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 60, 50));

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/intAdmin/cerrarR.png"))); // NOI18N
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel21.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel21MouseMoved(evt);
            }
        });
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel21MouseExited(evt);
            }
        });
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 60, 50));

        jLABELsEmanas.setBackground(new java.awt.Color(0, 0, 0));
        jLABELsEmanas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLABELsEmanas.setForeground(new java.awt.Color(255, 255, 255));
        jLABELsEmanas.setText("Patida");
        jPanel2.add(jLABELsEmanas, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        LabelPartida.setBackground(new java.awt.Color(0, 0, 0));
        LabelPartida.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        LabelPartida.setForeground(new java.awt.Color(255, 255, 255));
        LabelPartida.setText("Patida");
        jPanel2.add(LabelPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel1.setBackground(new java.awt.Color(0, 38, 28));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btngraficas2.setBackground(new java.awt.Color(0, 38, 28));
        btngraficas2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btngraficas2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btngraficas2MousePressed(evt);
            }
        });
        btngraficas2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Gráficas");
        btngraficas2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        indgraficas1.setBackground(new java.awt.Color(0, 38, 28));
        indgraficas1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        btngraficas2.add(indgraficas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        jPanel1.add(btngraficas2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 100, 40));

        btnInicio1.setBackground(new java.awt.Color(0, 38, 28));
        btnInicio1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInicio1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnInicio1MousePressed(evt);
            }
        });
        btnInicio1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Inicio");
        btnInicio1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        indInicio1.setBackground(new java.awt.Color(0, 38, 28));
        indInicio1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        btnInicio1.add(indInicio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        jPanel1.add(btnInicio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 100, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logoicon.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 60, 60));

        btngraficas.setBackground(new java.awt.Color(0, 38, 28));
        btngraficas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btngraficas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btngraficasMousePressed(evt);
            }
        });
        btngraficas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Mercado");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        btngraficas.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        indgraficas.setBackground(new java.awt.Color(0, 38, 28));
        indgraficas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        btngraficas.add(indgraficas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        jPanel1.add(btngraficas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 100, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Volver al login");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 80, 50));

        pnlPrincipal.setLayout(new java.awt.CardLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(151, 237, 138));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(153, 255, 153));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GotaIcon.png"))); // NOI18N
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, 60));

        LabelAgua.setBackground(new java.awt.Color(0, 0, 0));
        LabelAgua.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        LabelAgua.setText("0");
        jPanel5.add(LabelAgua, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 310, 90));

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("<html>Cultivos cosechados:</html>");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, 110, 50));

        LabeProduc.setBackground(new java.awt.Color(0, 0, 0));
        LabeProduc.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        LabeProduc.setText("0");
        jPanel4.add(LabeProduc, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 450, -1, -1));

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Semana:");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/inventarioicon.png"))); // NOI18N
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 70, 60));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/alturaicon.png"))); // NOI18N
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 70, 60));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/calendarioicon.png"))); // NOI18N
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 70, 60));

        LabelAltura.setBackground(new java.awt.Color(0, 0, 0));
        LabelAltura.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        LabelAltura.setText("0");
        jPanel4.add(LabelAltura, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, -1, -1));

        LabelSemanasCultivo.setBackground(new java.awt.Color(0, 0, 0));
        LabelSemanasCultivo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        LabelSemanasCultivo.setText("0");
        jPanel4.add(LabelSemanasCultivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, -1, -1));

        jPanel6.setBackground(new java.awt.Color(183, 255, 171));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbliconfinca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icoFinca.png"))); // NOI18N
        jPanel6.add(lbliconfinca, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 70, 60));

        lbliconuser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Usuario.png"))); // NOI18N
        jPanel6.add(lbliconuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, 60));

        LBNombreUsu.setBackground(new java.awt.Color(0, 0, 0));
        LBNombreUsu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        LBNombreUsu.setText("Diego Arbelaez");
        jPanel6.add(LBNombreUsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        LBNombreFinca.setBackground(new java.awt.Color(0, 0, 0));
        LBNombreFinca.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        LBNombreFinca.setText("Villa Austera");
        jPanel6.add(LBNombreFinca, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, -1, -1));

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 150));

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText("Altura:");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 510));

        paneljej.setBackground(new java.awt.Color(184, 242, 190));
        paneljej.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ListasDeCultivos.setBackground(new java.awt.Color(196, 232, 226));
        ListasDeCultivos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(196, 232, 226), 5, true));
        ListasDeCultivos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ListasDeCultivos.setForeground(new java.awt.Color(4, 22, 34));
        ListasDeCultivos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Zanahoria", "Maiz", "Tomate", "Pepino" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        ListasDeCultivos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ListasDeCultivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListasDeCultivosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ListasDeCultivos);

        paneljej.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 230, 318));

        jPanel8.setBackground(new java.awt.Color(225, 239, 236));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblsembrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sembrarIcond.png"))); // NOI18N
        lblsembrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblsembrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblsembrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblsembrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblsembrarMouseExited(evt);
            }
        });
        jPanel8.add(lblsembrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 100));

        lblregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/regaricon_1.png"))); // NOI18N
        lblregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblregarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblregarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblregarMouseExited(evt);
            }
        });
        jPanel8.add(lblregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 90, 80));

        lbleliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/EliminarPlantaIcon.png"))); // NOI18N
        lbleliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbleliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbleliminarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbleliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbleliminarMouseExited(evt);
            }
        });
        jPanel8.add(lbleliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 80, 80));

        paneljej.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 110, 320));

        lblsiguietne.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblsiguietne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/130884_1.png"))); // NOI18N
        lblsiguietne.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblsiguietne.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblsiguietneMouseMoved(evt);
            }
        });
        lblsiguietne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblsiguietneMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblsiguietneMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblsiguietneMouseExited(evt);
            }
        });
        paneljej.add(lblsiguietne, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 420, 70, 74));

        jLabel8.setBackground(new java.awt.Color(184, 242, 190));
        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel8.setText("Terminar Partida");
        jLabel8.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        paneljej.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 160, 30));

        LBNombreUsu1.setBackground(new java.awt.Color(0, 0, 0));
        LBNombreUsu1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LBNombreUsu1.setText("Recuerda darle clic a tus cultivos para ver su proceso.");
        paneljej.add(LBNombreUsu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, 20));

        TienesQueRegar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aguaGlobalddd.png"))); // NOI18N
        paneljej.add(TienesQueRegar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 32, 32));

        jPanel3.add(paneljej, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 410, 510));

        pnlPrincipal.add(jPanel3, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setBounds(0, 0, 819, 560);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        modSQL.Salida(usu);
    }//GEN-LAST:event_formWindowClosing

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        this.setState(VentanaCliente1.ICONIFIED);
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel21MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseMoved

    }//GEN-LAST:event_jLabel21MouseMoved

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(this, "¿Desea salir?", "Exit", dialog);
        if (result == 0) {
            modSQL.Salida(usu);
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel21MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseExited

    }//GEN-LAST:event_jLabel21MouseExited

    private void btngraficas2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btngraficas2MousePressed
        PantallaDeCargarPeque f = new PantallaDeCargarPeque();
        f.setLocationRelativeTo(this);
        f.setVisible(true);
        try {
            pnlgraficas = new pnlGraficas(Cultivos);
        } catch (IOException ex) {
            Logger.getLogger(VentanaCliente1.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!VentanaCliente1.Cultivos.isEmpty()) {
            pnlgraficas.LLenar();
            pnlgraficas.CargarLaVaina();
        }
        vista = (CardLayout) pnlPrincipal.getLayout();
        setColor(btngraficas2);
        indgraficas1.setBackground(new Color(39, 86, 43));
        resetColor(new JPanel[]{btnInicio1}, new JPanel[]{indInicio1});
        resetColor(new JPanel[]{btngraficas}, new JPanel[]{indgraficas});
        f.dispose();
        pnlPrincipal.add(pnlgraficas, "graficas");
        vista.show(pnlPrincipal, "graficas");
        SwingUtilities.updateComponentTreeUI(this);
        this.repaint();
    }//GEN-LAST:event_btngraficas2MousePressed

    private void btnInicio1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicio1MousePressed

        setColor(btnInicio1);
        indInicio1.setBackground(new Color(39, 86, 43));
        resetColor(new JPanel[]{btngraficas2}, new JPanel[]{indgraficas1});
        resetColor(new JPanel[]{btngraficas}, new JPanel[]{indgraficas});
        this.pnlgraficas.setVisible(false);
        this.pnlgraficas1.setVisible(false);

    }//GEN-LAST:event_btnInicio1MousePressed

    private void lblsiguietneMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblsiguietneMouseMoved

    }//GEN-LAST:event_lblsiguietneMouseMoved

    private void lblsiguietneMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblsiguietneMouseExited
        this.lblsiguietne.setIcon(new ImageIcon(getClass().getResource("/Imagenes/130884_1.png")));

    }//GEN-LAST:event_lblsiguietneMouseExited

    private void lblsembrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblsembrarMouseClicked

        if (vp == null) {
            vp = new VentanaPlantar(usu, this.CultivosCompa);
            vp.setLocationRelativeTo(this);
            vp.setVisible(true);

        }
    }//GEN-LAST:event_lblsembrarMouseClicked

    private void lblsiguietneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblsiguietneMouseClicked
        boolean xx = false;
//        Thread f =  new Thread(this);
//        f.start();
        PantallaDeCargarPeque f = new PantallaDeCargarPeque();
        f.setLocationRelativeTo(this);
        f.setVisible(true);
        Agua = 0;
        boolean x = false;
        int aux = 0;
        for (Cultivo Cultivo1 : Cultivos) {
            if (!Cultivo1.getAguaRegistrada()) {
                aux++;
            }
        }
        ArrayList<Integer> indices = new ArrayList<Integer>();
        ArrayList<Integer> indices2 = new ArrayList<Integer>();
        if (modelo.isEmpty()) {
            int dialog = JOptionPane.YES_NO_OPTION;
            f.setVisible(false);
            int result = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas pasar \n"
                    + "sin cultivar nada?", "Exit", dialog);
            if (result == 0) {
                this.tiempo++;
                if (!modSQL.PasarSemana(usu, this.tiempo)) {
                    JOptionPane.showMessageDialog(this, "Error en la base de datos ");
                }
                this.jLABELsEmanas.setText("Semana: " + this.tiempo);
                f.dispose();
            }
        } else {

            if (aux > 0) {
                int dialog = JOptionPane.YES_NO_OPTION;
                f.setVisible(false);
                int result = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas pasar \n"
                        + "de semana?, Tienes plantas sin regar", "Exit", dialog);
                if (result == 0) {
                    f.setVisible(true);
                    int conta = 0;
                    for (Cultivo Cultivo1 : Cultivos) {
                        int semana = (Cultivo1.getSemana() + 1);
                        double alturaNueva = semana * CultivosCompa.get(Cultivo1.getIdCultivo() - 1).getTasacCrsi();
                        alturaNueva = Math.round(alturaNueva * 100) / 100d;
                        if (!Cultivo1.getAguaRegistrada()) {
                            if (!Cultivo1.getBorrar()) {
                                int semaas = Cultivo1.getSemana() + 900;
                                modSQL.UpdateCultivosInactivos(Cultivo1, semaas);
                                indices2.add(conta);
                                x = true;

                            } else {
                                if (modSQL.UpdateCultivos(Cultivo1, semana, Cultivo1.getAltura(), Cultivo1.getAgua())) {
                                    Cultivo1.setSemana(semana);
                                    Cultivo1.setBorrar(false);
                                    String SemanasMalas = modSQL.BuscarSemanaMala(Cultivo1);
                                    if (SemanasMalas.equals("")) {
                                        SemanasMalas = semana + "-";
                                    } else {
                                        SemanasMalas = SemanasMalas + semana + "-";
                                    }
                                    modSQL.UpdateSemanaMala(Cultivo1, SemanasMalas);

                                } else {
                                    JOptionPane.showMessageDialog(this, "Error en la base de datos");
                                }

                            }

                        } else {
                            double ag = Cultivo1.getAgua() + 200;
                            if (modSQL.UpdateCultivos(Cultivo1, semana, alturaNueva, ag)) {
                                Cultivo1.setAltura(alturaNueva);
                                Cultivo1.setSemana(semana);
                                Cultivo1.setAgua(ag);
                                Cultivo1.setAguaRegistrada(false);
                                Cultivo1.setBorrar(true);
                            } else {
                                JOptionPane.showMessageDialog(this, "Error en la base de datos");
                            }

                        }
                        if (alturaNueva >= CultivosCompa.get(Cultivo1.getIdCultivo() - 1).getAlturAmAXIam()) {
                            this.produccion = this.produccion + CultivosCompa.get(Cultivo1.getIdCultivo() - 1).getCantidad();
                            this.CantidadVecesVendidas++;
                            this.PrecioFinal = this.PrecioFinal + (precios1[CultivosCompa.get(Cultivo1.getIdCultivo() - 1).getIdCultivo() - 1] * 6);
                            this.PrecioFinal = Math.round(this.PrecioFinal * 100) / 100d;
                            modSQL.UpdateOfertAdEMANDA(usu, this.CantidadVecesVendidas, this.PrecioFinal, this.produccion);
                            if (this.CantidadVecesVendidas == 1) {
                                pnlGraficas1.cANTIDAD.setText("Cultivos vendidos: " + this.CantidadVecesVendidas + "Lote");
                            } else {
                                pnlGraficas1.cANTIDAD.setText("Cultivos vendidos: " + this.CantidadVecesVendidas + "Lotes");

                            }
                            pnlGraficas1.producciones1312.setText("Cantidad de cultivos en total: " + this.produccion.toString() + " X Kg");
                            pnlGraficas1.Precio.setText("Capital Obtenido: " + this.PrecioFinal + " COP");

                            xx = true;
                            double[] valores = {0.25};
                            String[] Stringvalores = {Cultivo1.getNombreCultivo()};
                            try {
                                String fg = Cultivo1.getNombreCultivo() + "-" + "0.25";
                                c.EnviarInfo(fg);
                            } catch (IOException ex) {
                                Logger.getLogger(VentanaCliente1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            indices.add(conta);
                        }
                        conta++;
                    }f.dispose();
                    JOptionPane.showMessageDialog(this, "Se afecto la altura en tus cultivos\n"
                            + "Recuerda regar !!!!");
                }

            } else {
                this.TienesQueRegar.setVisible(true);
                this.tiempo++;
                if (!modSQL.PasarSemana(usu, this.tiempo)) {
                    JOptionPane.showMessageDialog(this, "Error en la base de datos ");
                }
                this.jLABELsEmanas.setText("Semana: " + this.tiempo);

                int conta = 0;

                for (Cultivo cul : VentanaCliente1.Cultivos) {

                    int semana = (cul.getSemana() + 1);
                    double alturaNueva = semana * CultivosCompa.get(cul.getIdCultivo() - 1).getTasacCrsi();
                    alturaNueva = Math.round(alturaNueva * 100) / 100d;
                    double ag = cul.getAgua() + 200;
                    if (modSQL.UpdateCultivos(cul, semana, alturaNueva, ag)) {
                        cul.setAltura(alturaNueva);
                        cul.setSemana(semana);
                        cul.setAgua(ag);
                        cul.setAguaRegistrada(false);
                        cul.setBorrar(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error en la base de datos");
                    }

                    if (alturaNueva >= CultivosCompa.get(cul.getIdCultivo() - 1).getAlturAmAXIam()) {
                        this.produccion = this.produccion + CultivosCompa.get(cul.getIdCultivo() - 1).getCantidad();
                        this.CantidadVecesVendidas++;
                        this.PrecioFinal = this.PrecioFinal + (precios1[CultivosCompa.get(cul.getIdCultivo() - 1).getIdCultivo() - 1] * 6);
                        this.PrecioFinal = Math.round(this.PrecioFinal * 100) / 100d;
                        modSQL.UpdateOfertAdEMANDA(usu, this.CantidadVecesVendidas, this.PrecioFinal, this.produccion);
                        if (this.CantidadVecesVendidas == 1) {
                            pnlGraficas1.cANTIDAD.setText("Cultivos vendidos: " + this.CantidadVecesVendidas + "Lote");
                        } else {
                            pnlGraficas1.cANTIDAD.setText("Cultivos vendidos: " + this.CantidadVecesVendidas + "Lotes");
                        }
                        pnlGraficas1.producciones1312.setText("Cantidad de cultivos en total: " + this.produccion.toString() + " X Kg");
                        pnlGraficas1.Precio.setText("Capital Obtenido: " + this.PrecioFinal + " COP");
                        xx = true;
                        double[] valores = {0.25};
                        String[] Stringvalores = {cul.getNombreCultivo()};
                        try {
                            String fg = cul.getNombreCultivo() + "-" + "0.25";
                            c.EnviarInfo(fg);
                        } catch (IOException ex) {
                            Logger.getLogger(VentanaCliente1.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        indices.add(conta);
                    }

                    conta++;
                }

            }

        }
        f.dispose();
        Collections.reverse(indices);
        for (int indice : indices) {
            this.Cultivos.remove(indice);
            modelo.remove(indice);
        }
        Collections.reverse(indices2);
        for (int integer : indices2) {
            this.Cultivos.remove(integer);
            modelo.remove(integer);
        }
        if (x) {
            JOptionPane.showMessageDialog(this, "Algunos cultivos han muerto :c", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        if (modelo.isEmpty()) {
            this.TienesQueRegar.setVisible(false);
        }
        this.LabeProduc.setText(this.produccion.toString());
        if (xx) {
            JOptionPane.showMessageDialog(this, "Has vendido cultivos");
        }


    }//GEN-LAST:event_lblsiguietneMouseClicked

    private void lblregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblregarMouseClicked
        if (modelo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay cultivos para regar");
        } else {
            int ee = 0;
            String aja = "";
            this.TienesQueRegar.setVisible(false);
            Agua++;
            if (Agua != 2) {
                for (Cultivo Cultivo1 : Cultivos) {
                    if (!Cultivo1.getAguaRegistrada()) {
                        ee +=200;
                        aja = "agua-"+ ee;
//                        if (ee == 0) {
//                            aja = "agua-200";
//                            ee++;
//                        } else {
//                            aja += "-agua-200";
//                        }
//                        try {
//                            //String df = "agua-200";
//                            //c.EnviarInfo(df);
//                            AguaBoolean = true;
//                        } catch (IOException ex) {
//                            Logger.getLogger(VentanaCliente1.class
//                                    .getName()).log(Level.SEVERE, null, ex);
//                        }
                        Cultivo1.setAguaRegistrada(true);
                        Cultivo1.setBorrar(true);
                    }
                }
                try {
                    c.EnviarInfo(aja);
                } catch (IOException ex) {
                    Logger.getLogger(VentanaCliente1.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(aja);
            } else {
                JOptionPane.showMessageDialog(this, "Recuerda no gastar tanta agua!! \n"
                        + "Ayudas al planeta y a los demas 🙏");
                Agua = 1;
            }
        }

    }//GEN-LAST:event_lblregarMouseClicked

    private void ListasDeCultivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListasDeCultivosMouseClicked
        try {
            this.LabelSemanasCultivo.setText("" + this.Cultivos.get(VentanaCliente1.ListasDeCultivos.getSelectedIndex()).getSemana());

            this.LabelAltura.setText(this.Cultivos.get(VentanaCliente1.ListasDeCultivos.getSelectedIndex()).getAltura() + " cm");
        } catch (Exception e) {
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_ListasDeCultivosMouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked

        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(this, "¿Desea acabar la partida?", "Exit", dialog);
        if (result == 0) {
            NombreFinca vb = new NombreFinca(usu, modSQL);
            vb.setVisible(true);
            this.dispose();
            this.NuepARTIDA = modSQL.NumeroPartida(usu);
            this.LabelPartida.setText("Partida: " + this.NuepARTIDA);
            this.tiempo = 0;
            this.CantidadVecesVendidas = 0;
            this.PrecioFinal = 0;

            this.jLABELsEmanas.setText("Semana: " + this.tiempo);
            this.produccion = 0;
            this.LabeProduc.setText(this.produccion.toString());
            this.modelo.clear();
        }
    }//GEN-LAST:event_jLabel8MouseClicked

    private void lbleliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbleliminarMouseClicked
        if (!this.ListasDeCultivos.isSelectionEmpty()) {
            int dialog = JOptionPane.YES_NO_OPTION;
            int result = JOptionPane.showConfirmDialog(this, "¿Deseas eliminar "
                    + this.Cultivos.get(this.ListasDeCultivos.getSelectedIndex()).getNombreCultivo() + "\n" + "con semanas "
                    + this.Cultivos.get(VentanaCliente1.ListasDeCultivos.getSelectedIndex()).getSemana() + "\n" + "con altura "
                    + this.Cultivos.get(VentanaCliente1.ListasDeCultivos.getSelectedIndex()).getAltura() + " cm ?", "Aviso", dialog);
            if (result == 0) {
                int semaas = this.Cultivos.get(VentanaCliente1.ListasDeCultivos.getSelectedIndex()).getSemana() + 900;
                modSQL.UpdateCultivosInactivos(this.Cultivos.get(this.ListasDeCultivos.getSelectedIndex()), semaas);
                this.Cultivos.remove(this.ListasDeCultivos.getSelectedIndex());
                this.modelo.remove(VentanaCliente1.ListasDeCultivos.getSelectedIndex());

            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un cultivo");
        }
        if (this.Cultivos.isEmpty()) {
            this.TienesQueRegar.setVisible(false);
        }
    }//GEN-LAST:event_lbleliminarMouseClicked


    private void lblsembrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblsembrarMouseEntered

        this.lblsembrar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/SembrarOpaco.png")));
    }//GEN-LAST:event_lblsembrarMouseEntered

    private void lblsiguietneMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblsiguietneMouseEntered
        this.lblsiguietne.setIcon(new ImageIcon(getClass().getResource("/Recursos/Se2.png")));
    }//GEN-LAST:event_lblsiguietneMouseEntered

    int xx, yy;
    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed

    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged

    }//GEN-LAST:event_jPanel2MouseDragged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        xx = evt.getX();
        yy = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xx, y - yy);        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseDragged

    private void btngraficasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btngraficasMousePressed

        vista = (CardLayout) pnlPrincipal.getLayout();
        setColor(btngraficas);
        indgraficas.setBackground(new Color(39, 86, 43));
        resetColor(new JPanel[]{btnInicio1}, new JPanel[]{indInicio1});
        resetColor(new JPanel[]{btngraficas2}, new JPanel[]{indgraficas1});
        pnlPrincipal.add(pnlgraficas1, "graficas");
        vista.show(pnlPrincipal, "graficas");
        SwingUtilities.updateComponentTreeUI(this);
        this.repaint();
    }//GEN-LAST:event_btngraficasMousePressed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        vista = (CardLayout) pnlPrincipal.getLayout();
        setColor(btngraficas);
        indgraficas.setBackground(new Color(39, 86, 43));
        resetColor(new JPanel[]{btnInicio1}, new JPanel[]{indInicio1});
        resetColor(new JPanel[]{btngraficas2}, new JPanel[]{indgraficas1});
        pnlPrincipal.add(pnlgraficas1, "graficas");
        vista.show(pnlPrincipal, "graficas");
        SwingUtilities.updateComponentTreeUI(this);
        this.repaint();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void lblsembrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblsembrarMouseExited
        this.lblsembrar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/sembrarIcond.png")));
    }//GEN-LAST:event_lblsembrarMouseExited

    private void lblregarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblregarMouseEntered
        this.lblregar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/intAdmin/AguaOpaca.png")));         // TODO add your handling code here:
    }//GEN-LAST:event_lblregarMouseEntered

    private void lblregarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblregarMouseExited
        this.lblregar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/intAdmin/regadera.png")));
    }//GEN-LAST:event_lblregarMouseExited

    private void lbleliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbleliminarMouseEntered
        this.lbleliminar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/EliminarPlantaOpaca.png")));
    }//GEN-LAST:event_lbleliminarMouseEntered

    private void lbleliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbleliminarMouseExited
        this.lbleliminar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/eliminarplanta.png")));
    }//GEN-LAST:event_lbleliminarMouseExited

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(this, "¿Deseas regresar al login?", "Exit", dialog);
        if (result == 0) {
            modSQL.Salida(usu);
            this.dispose();
            Loginv lg = new Loginv();
            lg.setVisible(true);
        }
    }//GEN-LAST:event_jLabel6MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaCliente1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaCliente1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaCliente1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaCliente1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new VentanaCliente1().setVisible(true);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Servidor no encontrado");
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LBNombreFinca;
    private javax.swing.JLabel LBNombreUsu;
    private javax.swing.JLabel LBNombreUsu1;
    private javax.swing.JLabel LabeProduc;
    private javax.swing.JLabel LabelAgua;
    private javax.swing.JLabel LabelAltura;
    private javax.swing.JLabel LabelPartida;
    private javax.swing.JLabel LabelSemanasCultivo;
    public static javax.swing.JList<String> ListasDeCultivos;
    public static javax.swing.JLabel TienesQueRegar;
    private javax.swing.JPanel btnInicio1;
    private javax.swing.JPanel btngraficas;
    private javax.swing.JPanel btngraficas2;
    private javax.swing.JPanel indInicio1;
    private javax.swing.JPanel indgraficas;
    private javax.swing.JPanel indgraficas1;
    private javax.swing.JLabel jLABELsEmanas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbleliminar;
    private javax.swing.JLabel lbliconfinca;
    private javax.swing.JLabel lbliconuser;
    private javax.swing.JLabel lblregar;
    private javax.swing.JLabel lblsembrar;
    private javax.swing.JLabel lblsiguietne;
    private javax.swing.JPanel paneljej;
    private javax.swing.JPanel pnlPrincipal;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(java.util.Observable o, Object arg) {
        String Da = (String) arg;
        String[] Datos = Da.split("-");
        int axu = 0;
        for (String Dato : Datos) {
            switch (Dato) {
                case "Zanahoria":
                    VentanaCliente1.precios1[0] = Double.parseDouble(Datos[axu + 1]);
                    pnlGraficas1.NombreCultiZana.setText(Dato + " X 500g");
                    VentanaCliente1.precios1[0] = Math.round(VentanaCliente1.precios1[0] * 100) / 100d;
                    pnlGraficas1.PrecioCulviZana.setText("$" + String.valueOf(VentanaCliente1.precios1[0]) + " COP");
                    if (VentanaCliente1.precios1[0] != VentanaCliente1.CultivosCompa.get(0).getPrecio()) {
                        if (VentanaCliente1.precios1[0] > VentanaCliente1.CultivosCompa.get(0).getPrecio()) {
                            pnlGraficas1.OfertaZana.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Sube.png")));
                            pnlGraficas1.DemandaZana.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Baja.png")));
                        } else {
                            pnlGraficas1.OfertaZana.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Baja.png")));
                            pnlGraficas1.DemandaZana.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Sube.png")));
                        }
                    } else {
                        pnlGraficas1.DemandaZana.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Igual.png")));
                        pnlGraficas1.OfertaZana.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Igual.png")));
                    }
                    break;
                case "Maíz":
                    this.precios1[1] = Double.parseDouble(Datos[axu + 1]);
                    pnlGraficas1.NombreCultiMaiz.setText(Dato + " X Kg");
                    VentanaCliente1.precios1[1] = Math.round(VentanaCliente1.precios1[1] * 100) / 100d;
                    pnlGraficas1.PrecioCulviMaiz.setText("$" + String.valueOf(VentanaCliente1.precios1[1]) + " COP");
                    if (VentanaCliente1.precios1[1] != VentanaCliente1.CultivosCompa.get(1).getPrecio()) {
                        if (VentanaCliente1.precios1[1] > VentanaCliente1.CultivosCompa.get(1).getPrecio()) {
                            pnlGraficas1.OfertaMaiz.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Sube.png")));
                            pnlGraficas1.DemandaMaiz.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Baja.png")));
                        } else {
                            pnlGraficas1.OfertaMaiz.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Baja.png")));
                            pnlGraficas1.DemandaMaiz.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Sube.png")));
                        }
                    } else {
                        pnlGraficas1.DemandaMaiz.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Igual.png")));
                        pnlGraficas1.OfertaMaiz.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Igual.png")));
                    }
                    break;
                case "Papa":
                    this.precios1[2] = Double.parseDouble(Datos[axu + 1]);
                    pnlGraficas1.NombreCultipapa.setText(Dato + " X Kg");
                    VentanaCliente1.precios1[2] = Math.round(VentanaCliente1.precios1[2] * 100) / 100d;
                    pnlGraficas1.PrecioCulvipapa.setText("$" + String.valueOf(VentanaCliente1.precios1[2]) + " COP");
                    if (VentanaCliente1.precios1[2] != VentanaCliente1.CultivosCompa.get(2).getPrecio()) {
                        if (VentanaCliente1.precios1[2] > VentanaCliente1.CultivosCompa.get(2).getPrecio()) {
                            pnlGraficas1.Ofertapapa.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Sube.png")));
                            pnlGraficas1.Demandapapa.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Baja.png")));
                        } else {
                            pnlGraficas1.Ofertapapa.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Baja.png")));
                            pnlGraficas1.Demandapapa.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Sube.png")));
                        }
                    } else {
                        pnlGraficas1.Demandapapa.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Igual.png")));
                        pnlGraficas1.Ofertapapa.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Igual.png")));
                    }
                    break;
                case "Tomate":
                    this.precios1[3] = Double.parseDouble(Datos[axu + 1]);
                    pnlGraficas1.NombreCultiToma.setText(Dato + " X 500g");
                    VentanaCliente1.precios1[3] = Math.round(VentanaCliente1.precios1[3] * 100) / 100d;
                    pnlGraficas1.PrecioCulviToma.setText("$" + String.valueOf(VentanaCliente1.precios1[3]) + " COP");
                    if (VentanaCliente1.precios1[3] != VentanaCliente1.CultivosCompa.get(3).getPrecio()) {
                        if (VentanaCliente1.precios1[3] > VentanaCliente1.CultivosCompa.get(3).getPrecio()) {
                            pnlGraficas1.OfertaToma.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Sube.png")));
                            pnlGraficas1.DemandaToma.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Baja.png")));
                        } else {
                            pnlGraficas1.OfertaToma.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Baja.png")));
                            pnlGraficas1.DemandaToma.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Sube.png")));
                        }
                    } else {
                        pnlGraficas1.DemandaToma.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Igual.png")));
                        pnlGraficas1.OfertaToma.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Igual.png")));
                    }
                    double in3 = -999;
                    int sd3 = 0;
                    for (int i = 0; i < 4; i++) {
                        if (VentanaCliente1.precios1[i] > in3) {
                            in3 = VentanaCliente1.precios1[i];
                            sd3 = i;
                        }
                    }
                    pnlGraficas1.Recomentdacion.setText("<html>Cosechar " + VentanaCliente1.CultivosCompa.get(sd3).getNombreCultivo() + " \n"
                            + "con un valor de $" + VentanaCliente1.precios1[sd3] + " COP" + "</html>");
                    break;
                case "agua":
                    this.LabelAgua.setText(String.valueOf(Double.parseDouble(Datos[axu + 1])) + " L");
                    break;
            }
            axu++;
        }
    }

}
