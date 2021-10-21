/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import ConexionBD.Cultivo;
import ConexionBD.CultivosCompara;
import ConexionBD.Usuarios;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.chart.NumberAxis;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import static proyecto.VentanaCliente1.CultivosCompa;

/**
 *
 * @author Acer Nitro 5
 */
public class pnlGraficas1 extends javax.swing.JPanel {

    /**
     * Creates new form pnlGraficas
     */
    


    public pnlGraficas1() {
        initComponents();

    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        DemandaToma = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        NombreCultiToma = new javax.swing.JLabel();
        PrecioCulviToma = new javax.swing.JLabel();
        OfertaToma = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        Demandapapa = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        NombreCultipapa = new javax.swing.JLabel();
        PrecioCulvipapa = new javax.swing.JLabel();
        Ofertapapa = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        DemandaMaiz = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        NombreCultiMaiz = new javax.swing.JLabel();
        PrecioCulviMaiz = new javax.swing.JLabel();
        OfertaMaiz = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        DemandaZana = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        NombreCultiZana = new javax.swing.JLabel();
        PrecioCulviZana = new javax.swing.JLabel();
        OfertaZana = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Recomentdacion = new javax.swing.JLabel();
        cANTIDAD = new javax.swing.JLabel();
        Precio = new javax.swing.JLabel();
        producciones1312 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(184, 242, 190));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(194, 221, 199));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Demanada:");
        jLabel17.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 80, 30));

        DemandaToma.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        DemandaToma.setText("-");
        jPanel7.add(DemandaToma, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 32, 32));

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel18.setText("Precio:");
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 50, 30));

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel19.setText("Oferta:");
        jPanel7.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 60, 30));

        NombreCultiToma.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        NombreCultiToma.setText("-");
        jPanel7.add(NombreCultiToma, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 130, 30));

        PrecioCulviToma.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        PrecioCulviToma.setText("-");
        jPanel7.add(PrecioCulviToma, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 110, 30));

        OfertaToma.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        OfertaToma.setText("-");
        jPanel7.add(OfertaToma, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 32, 32));

        add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 180, 270));

        jPanel6.setBackground(new java.awt.Color(194, 221, 199));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Demanada:");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, 30));

        Demandapapa.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Demandapapa.setText("-");
        jPanel6.add(Demandapapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 32, 32));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setText("Precio:");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 50, 30));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setText("Oferta:");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 50, 30));

        NombreCultipapa.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        NombreCultipapa.setText("-");
        jPanel6.add(NombreCultipapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 100, 30));

        PrecioCulvipapa.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        PrecioCulvipapa.setText("-");
        jPanel6.add(PrecioCulvipapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 100, 30));

        Ofertapapa.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Ofertapapa.setText("-");
        jPanel6.add(Ofertapapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 32, 32));

        add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, -1, 270));

        jPanel5.setBackground(new java.awt.Color(194, 221, 199));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Demanada:");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 80, 30));

        DemandaMaiz.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        DemandaMaiz.setText("-");
        jPanel5.add(DemandaMaiz, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 32, 32));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setText("Precio:");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 50, 30));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setText("Oferta:");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 50, 30));

        NombreCultiMaiz.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        NombreCultiMaiz.setText("-");
        jPanel5.add(NombreCultiMaiz, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 90, 30));

        PrecioCulviMaiz.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        PrecioCulviMaiz.setText("-");
        jPanel5.add(PrecioCulviMaiz, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 90, 30));

        OfertaMaiz.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        OfertaMaiz.setText("-");
        jPanel5.add(OfertaMaiz, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 32, 32));

        add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 180, 270));

        jPanel3.setBackground(new java.awt.Color(194, 221, 199));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Demanada:");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 80, 30));

        DemandaZana.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        DemandaZana.setText("-");
        jPanel3.add(DemandaZana, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 32, 32));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Precio:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 50, 30));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Oferta:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 60, 30));

        NombreCultiZana.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        NombreCultiZana.setText("-");
        jPanel3.add(NombreCultiZana, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 120, 30));

        PrecioCulviZana.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        PrecioCulviZana.setText("-");
        jPanel3.add(PrecioCulviZana, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 100, 30));

        OfertaZana.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        OfertaZana.setText("-");
        jPanel3.add(OfertaZana, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 32, 32));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 170, 270));

        jPanel1.setBackground(new java.awt.Color(194, 221, 199));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Recomendacion:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        Recomentdacion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Recomentdacion.setText("-");
        Recomentdacion.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(Recomentdacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 500, 40));

        cANTIDAD.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cANTIDAD.setText("jLabel9");
        jPanel1.add(cANTIDAD, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        Precio.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Precio.setText("jLabel11");
        jPanel1.add(Precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        producciones1312.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        producciones1312.setText("jLabel9");
        jPanel1.add(producciones1312, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 700, 190));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/intAdmin/FondoAdmin.jpg"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 800, 693));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/intAdmin/FondoAdmin.jpg"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 520));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel DemandaMaiz;
    public static javax.swing.JLabel DemandaToma;
    public static javax.swing.JLabel DemandaZana;
    public static javax.swing.JLabel Demandapapa;
    public static javax.swing.JLabel NombreCultiMaiz;
    public static javax.swing.JLabel NombreCultiToma;
    public static javax.swing.JLabel NombreCultiZana;
    public static javax.swing.JLabel NombreCultipapa;
    public static javax.swing.JLabel OfertaMaiz;
    public static javax.swing.JLabel OfertaToma;
    public static javax.swing.JLabel OfertaZana;
    public static javax.swing.JLabel Ofertapapa;
    public static javax.swing.JLabel Precio;
    public static javax.swing.JLabel PrecioCulviMaiz;
    public static javax.swing.JLabel PrecioCulviToma;
    public static javax.swing.JLabel PrecioCulviZana;
    public static javax.swing.JLabel PrecioCulvipapa;
    public static javax.swing.JLabel Recomentdacion;
    public static javax.swing.JLabel cANTIDAD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    public static javax.swing.JLabel producciones1312;
    // End of variables declaration//GEN-END:variables

}
