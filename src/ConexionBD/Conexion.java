package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {

    //Nombre de la base de datos
    private final String base = "proyecto";
    //private final String base = "agrolearningp2";
    //Usuario de la base de datos
     private final String user = "admin";
    //private final String user = "agrolearningAdmin";
    //private final String contraseña = "Mq64!c1I0xS-";
    private final String contraseña = "Santiago123";
    //private final String contraseña = "12345";
    //Donde nos vamos a conectar
    //private final String url = "jdbc:mysql://localhost:" + 3306 + "/" + base;
   private final String url = "jdbc:mysql://agrolearndb.c6yfxy06ajmg.us-east-1.rds.amazonaws.com:" + 3306 + "/" + base;
     //private final String url = "jdbc:mysql://207.248.81.156:" + 3306 + "/" + base;
    private Connection con = null;

    //Clase para la conexión a Mysql
    public Connection getConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, contraseña);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en la base de datos");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en la base de datos");
        }
        return con;
    }
}
