/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import ConexionBD.CultivosCompara;
import ConexionBD.Hash;
import ConexionBD.SQLUsuarios;
import ConexionBD.Usuarios;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer Nitro 5
 */
public class pnlRegPlantas extends javax.swing.JPanel {

    /**
     * Creates new form pnlRegPlantas
     */
    public pnlRegPlantas() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        txtTasa = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        txtPreocio = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        EstadoUsuBox = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtnombre = new javax.swing.JTextField();
        TxtAlturamAX = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        txtsemanas = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/intAdmin/OrquideaSilueta.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 160, 150));

        jPanel3.setBackground(new java.awt.Color(194, 221, 199));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 38, 28));
        jLabel6.setText("<html> Estado del cultivo:</html>");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 120, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 38, 28));
        jLabel7.setText("<html>Tasa de crecimiento:</html>");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 110, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 38, 28));
        jLabel8.setText("<html>Cantidad:</html>");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 290, 20));

        txtTasa.setBackground(new java.awt.Color(194, 221, 199));
        txtTasa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTasa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.add(txtTasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 160, 30));

        txtCantidad.setBackground(new java.awt.Color(194, 221, 199));
        txtCantidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCantidad.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 160, 30));

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 290, 20));

        txtPreocio.setBackground(new java.awt.Color(194, 221, 199));
        txtPreocio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPreocio.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.add(txtPreocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 160, 30));

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 290, 20));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/intAdmin/registarcultivoOFF.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/intAdmin/registarON.png"))); // NOI18N
        jButton2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/intAdmin/registarON.png"))); // NOI18N
        jButton2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/intAdmin/registarON.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 150, 50));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 38, 28));
        jLabel9.setText("<html> Precio:</html>");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 120, -1));

        EstadoUsuBox.setBackground(new java.awt.Color(178, 249, 226));
        EstadoUsuBox.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        EstadoUsuBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        jPanel3.add(EstadoUsuBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, 130, 20));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 210, 370, 440));

        jPanel2.setBackground(new java.awt.Color(194, 221, 199));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 38, 28));
        jLabel2.setText("<html>Lapso de tiempo para cosecha(semanas):</html>");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 120, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 38, 28));
        jLabel4.setText("<html>Altura m??xima:</html>");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 290, 20));

        txtnombre.setBackground(new java.awt.Color(194, 221, 199));
        txtnombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtnombre.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 160, 30));

        TxtAlturamAX.setBackground(new java.awt.Color(194, 221, 199));
        TxtAlturamAX.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TxtAlturamAX.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.add(TxtAlturamAX, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 160, 30));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 290, 20));

        txtsemanas.setBackground(new java.awt.Color(194, 221, 199));
        txtsemanas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtsemanas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.add(txtsemanas, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 160, 30));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 290, 20));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 38, 28));
        jLabel10.setText("<html>Nombre del Producto:</html>");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 110, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 350, 440));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/intAdmin/FondoAdmin.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, -7, 800, 710));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JOptionPane.showMessageDialog(this, "Muy prondo habilitaremos este registro");
        /*
        SQLUsuarios modSQL = new SQLUsuarios();
        CultivosCompara mod = new CultivosCompara();
        //Campos a evaluar
        //Se convierte a String
        if (txtnombre.equals("") && TxtAlturamAX.equals("") && txtsemanas.equals("") && txtTasa.equals("")
                && txtCantidad.equals("") && txtPreocio.equals("")) {
            JOptionPane.showMessageDialog(this, "Ingresa todos los campos");
        } else {

            mod.setNombreCultivo(this.txtnombre.getText());
            mod.setAlturAmAXIam(Double.parseDouble(this.TxtAlturamAX.getText()));
            mod.setSemanaMax(Integer.parseInt(this.txtsemanas.getText()));
            mod.setTasacCrsi(Double.parseDouble(this.txtTasa.getText()));
            mod.setCantidad(Integer.parseInt(this.txtCantidad.getText()));
            mod.setPrecio(Double.parseDouble(this.txtPreocio.getText()));
            mod.setEstado((this.EstadoUsuBox.getSelectedIndex() + 1));

            if (modSQL.registrarCultivo(mod)) {
                Limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar.");
            }
        }
*/

    }//GEN-LAST:event_jButton2ActionPerformed

    private void Limpiar() {
        txtnombre.setText("");
        TxtAlturamAX.setText("");
        txtsemanas.setText("");
        txtTasa.setText("");
        txtCantidad.setText("");
        txtPreocio.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> EstadoUsuBox;
    private javax.swing.JTextField TxtAlturamAX;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtPreocio;
    private javax.swing.JTextField txtTasa;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtsemanas;
    // End of variables declaration//GEN-END:variables
}
