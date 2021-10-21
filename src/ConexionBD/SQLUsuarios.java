package ConexionBD;
//Realizará las Consultas a la base de datos

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import proyecto.NombreFinca;

//Hereda la conexion de mysql
public class SQLUsuarios extends Conexion {

    public boolean registrar(Usuarios usr) {
        //Se realiza una insercion a MYSQL
        //Se prepara la consulta y hacer la conexion
        PreparedStatement ps = null;
        //Viene de la clase conexion y se llama directamente debido a que se heredó 
        Connection con = getConexion();
        //Contiene la consulta a la base de datos
        //Se colocan parentesis debido a que más abajo se indicaran los datos
        String sql = "INSERT INTO usuarios (Nombres, Apellidos, "
                + "Numero_celular, correo, nombre_usuario, contraseña,tipo_usuario_id,EstadoCultivo_idEstadoCultivo,DataDeRegistro) VALUES(?,?,?,?,?,?,?,?,current_timestamp())";

        try {
            if (!usr.getContraseña().equals("") && !usr.getNombres().equals("") && !usr.getApellidos().equals("")
                    && !usr.getNumero_celular().equals("")
                    && !usr.getCorreo().equals("") && !usr.getNombre_usuario().equals("")) {
                //Enviamos la consulta
                ps = con.prepareStatement(sql);
                ps.setString(1, usr.getNombres());
                ps.setString(2, usr.getApellidos());
                ps.setString(3, usr.getNumero_celular());
                ps.setString(4, usr.getCorreo());
                ps.setString(5, usr.getNombre_usuario());
                ps.setString(6, usr.getContraseña());
                ps.setInt(7, usr.getId_tipo());
                ps.setInt(8, usr.getEstadoUsu());
                //Se ejecuta la insercion
                ps.execute();
                JOptionPane.showMessageDialog(null, "El registro se ha realizado.");
                //Si se inserta correctamente
            } else {
                JOptionPane.showMessageDialog(null, "Ingresa todos los campos");
            }

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            //En caso de error devuelve falso
            return false;
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public boolean registrarCultivo(CultivosCompara usr) {
        //Se realiza una insercion a MYSQL
        //Se prepara la consulta y hacer la conexion
        PreparedStatement ps = null;
        //Viene de la clase conexion y se llama directamente debido a que se heredó 
        Connection con = getConexion();
        //Contiene la consulta a la base de datos
        //Se colocan parentesis debido a que más abajo se indicaran los datos
        String sql = "INSERT INTO cultivos (NombreCultivo, AlturaMaxima, SemanaMax, TasaCresi, Cantidad,\n"
                + "                PrecioInicial, EstadoCultivo_idEstadoCultivo) VALUES(?,?,?,?,?,?,?)";

        try {

            //Enviamos la consulta
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getNombreCultivo());
            ps.setDouble(2, usr.getAlturAmAXIam());
            ps.setInt(3, usr.getSemanaMax());
            ps.setDouble(4, usr.getTasacCrsi());
            ps.setInt(5, usr.getCantidad());
            ps.setDouble(6, usr.getPrecio());
            ps.setInt(7, usr.getEstado());
            //Se ejecuta la insercion
            ps.execute();
            JOptionPane.showMessageDialog(null, "El registro se ha realizado.");
            //Si se inserta correctamente

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            //En caso de error devuelve falso
            return false;
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public boolean registrarPartida(Usuarios usr, String Nf) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO partidasusuarios (usuarios_IDUsuario ,Nombre_finca, Semanas ) VALUES(?,?,?)";

        try {

            ps = con.prepareStatement(sql);
            ps.setInt(1, usr.getIDUsuario());
            ps.setNString(2, Nf);
            ps.setInt(3, 0);
            //Se ejecuta la insercion
            ps.execute();
            //Si se inserta correctamente

            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos aquidddd" + ex);
            //En caso de error devuelve falso
            return false;
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public boolean BuscarPartida(Usuarios usr) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "SELECT idPartidasUsuarios, Nombre_finca , Semanas  FROM partidasusuarios WHERE idPartidasUsuarios =? ";

        try {

            int id = IDNumeroPartida(usr);
            if (id == 0) {
                NombreFinca c = new NombreFinca(usr, this);
                c.setVisible(true);
                return false;
            }
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            //Se ejecuta la insercion
            rs = ps.executeQuery();
            //Si se inserta correctamente

            rs.next();
            if (rs.getInt(1) != 0) {
                usr.setNPartida(rs.getInt(1));
                usr.setNombre_finca(rs.getString(2));
                usr.setSemanasJuga(rs.getInt(3));
            } else {
                NombreFinca c = new NombreFinca(usr, this);
                c.setVisible(true);
                return false;

            }
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos Aqui Bus  " + ex);
            //En caso de error devuelve falso
            return false;
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public Integer IDNumeroPartida(Usuarios usr) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "SELECT max(idPartidasUsuarios)  FROM partidasusuarios WHERE usuarios_IDUsuario= ?";

        try {

            ps = con.prepareStatement(sql);
            ps.setInt(1, usr.getIDUsuario());
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos " + ex);
            //En caso de error devuelve falso
            return 0;
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public Integer NumeroPartida(Usuarios usr) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "SELECT count(idPartidasUsuarios) FROM partidasusuarios WHERE usuarios_IDUsuario = ?";

        try {

            ps = con.prepareStatement(sql);
            ps.setInt(1, usr.getIDUsuario());
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos " + ex);
            //En caso de error devuelve falso
            return 0;
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public boolean Login(Usuarios usr) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "SELECT nombre_usuario, contraseña, Apellidos,IDUsuario, Nombres,EstadoCultivo_idEstadoCultivo,tipo_usuario_id FROM usuarios WHERE BINARY nombre_usuario = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getNombre_usuario());
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            if (rs.next()) {
                if (usr.getContraseña().equals(rs.getString(2))) {

                    usr.setNombres(rs.getNString(5));
                    usr.setApellidos(rs.getNString(3));
                    usr.setIDUsuario(rs.getInt(4));
                    usr.setEstadoUsu(rs.getInt(6));
                    usr.setId_tipo(rs.getInt(7));

                    return true;
                } else {
                    return false;
                }

            }
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    public boolean RegeistrarEntreda(Usuarios usr) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO registrosdeentradassalidas "
                + "(usuarios_IDUsuario,Entrada,Salida) VALUES(?,current_timestamp(),'0000-00-00 00:00:00')";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, usr.getIDUsuario());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos Aqio");
            return false;
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    public boolean RegeistrarCultivo(Usuarios usr, double agua, int seman, double altura) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO procesoscultivos (PartidasUsuarios_idPartidasUsuarios"
                + ",Agua,Semana,AlturaCultivo) VALUES(?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, usr.getNPartida());
            ps.setDouble(2, agua);
            ps.setInt(3, seman);
            ps.setDouble(4, altura);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos " + ex);
            return false;
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    public boolean RegeistrarCultivoConProceso(Usuarios usr, int id) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO cultivosvsprocesos (Cultivos_idCultivos"
                + ",ProcesosCultivos_CODIGO_Cultivo,CultivosCosechados,Precio) VALUES(?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, IdProcesoCultivo(usr));
            ps.setNString(3, "");
            ps.setDouble(4, 0);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos " + ex);
            return false;
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    public int IdProcesoCultivo(Usuarios usr) {

        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "SELECT max(CODIGO_Cultivo) FROM procesoscultivos WHERE PartidasUsuarios_idPartidasUsuarios = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, usr.getNPartida());
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return 0;
    }

    public String BuscarSemanaMala(Cultivo usr) {

        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "SELECT CultivosCosechados FROM cultivosvsprocesos WHERE ProcesosCultivos_CODIGO_Cultivo = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, usr.getIDproceso());
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            if (rs.next()) {
                return rs.getNString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return "";
                
    }
    
    public boolean UpdateSemanaMala(Cultivo cul, String sema) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE cultivosvsprocesos SET CultivosCosechados = ? WHERE ProcesosCultivos_CODIGO_Cultivo = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, sema);
            ps.setInt(2, cul.getIDproceso());
            ps.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos aqui " + ex);
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        return true;
    }

    public boolean Salida(Usuarios usr) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE registrosdeentradassalidas\n"
                + "SET Salida = current_timestamp()  WHERE CODIGO_Registro = ?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, IdRegistroSalida(usr));
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos " + ex);
            return false;
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    public int IdRegistroSalida(Usuarios usr) {

        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "SELECT max(CODIGO_Registro) FROM registrosdeentradassalidas WHERE usuarios_IDUsuario = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, usr.getIDUsuario());
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return 0;
    }

    public boolean PasarSemana(Usuarios usr, int tiempo) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE partidasusuarios\n"
                + "SET Semanas = ?  WHERE idPartidasUsuarios = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, tiempo);
            ps.setInt(2, usr.getNPartida());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos " + ex);
            return false;
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    public ArrayList<Cultivo> CargarDatosInicioCultivos(Usuarios usr) {
        ArrayList<Cultivo> Datos = new ArrayList<Cultivo>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = getConexion();
        //String sql = "SELECT Cultivo FROM registrodepartidaporsema WHERE CODIGO_partida = ?";
        String sql = "select  cultivosvsprocesos.idCultivoVsProce,cultivos.NombreCultivo,procesoscultivos.Semana, procesoscultivos.AlturaCultivo, cultivos.idCultivos,\n"
                + "procesoscultivos.CODIGO_Cultivo, procesoscultivos.Agua\n"
                + "from cultivosvsprocesos , procesoscultivos, cultivos\n"
                + "WHERE  procesoscultivos.PartidasUsuarios_idPartidasUsuarios = ?  and cultivosvsprocesos.Cultivos_idCultivos = cultivos.idCultivos\n"
                + "and cultivosvsprocesos.ProcesosCultivos_CODIGO_Cultivo = procesoscultivos.CODIGO_Cultivo \n"
                + "group by  cultivosvsprocesos.idCultivoVsProce  order by cultivosvsprocesos.idCultivoVsProce";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, usr.getNPartida());
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            while (rs.next()) {

                Cultivo axu = new Cultivo(rs.getNString(2), rs.getInt(5), rs.getInt(6), rs.getDouble(4), rs.getInt(3),
                        rs.getDouble(7), rs.getInt(1));

                Datos.add(axu);
            }
            return Datos;
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return Datos;
    }

    public ArrayList<CultivosCompara> CultivosCOmpa() {
        ArrayList<CultivosCompara> Datos = new ArrayList<CultivosCompara>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "SELECT idCultivos , NombreCultivo, AlturaMaxima, SemanaMax, TasaCresi, Cantidad,PrecioInicial FROM cultivos";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            while (rs.next()) {
                CultivosCompara aux = new CultivosCompara(rs.getNString(2), rs.getInt(1), rs.getInt(4), rs.getDouble(3),
                        rs.getDouble(5), rs.getInt(6), rs.getDouble(7));
                Datos.add(aux);
            }
            return Datos;
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return Datos;
    }

    public ArrayList<Estados> Estados() {

        ArrayList<Estados> Datos = new ArrayList<Estados>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "SELECT idEstadoCultivo,NombreDeEstado FROM estadocultivo";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            while (rs.next()) {
                Estados et = new Estados(rs.getInt(1), rs.getNString(2));
                Datos.add(et);
            }
            return Datos;
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return Datos;
    }

    public ArrayList<TipoUsuarios> TiposUsu() {

        ArrayList<TipoUsuarios> Datos = new ArrayList<TipoUsuarios>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "SELECT id,Nombre_usuario FROM tipo_usuario";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            while (rs.next()) {
                TipoUsuarios et = new TipoUsuarios(rs.getInt(1), rs.getNString(2));
                Datos.add(et);
            }
            return Datos;
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return Datos;
    }

    public boolean UpdateCultivos(Cultivo cul, int sema, double altu, double agua) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE procesoscultivos\n"
                + "SET Semana = ?, AlturaCultivo = ?,  Agua =? WHERE CODIGO_Cultivo = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, sema);
            ps.setDouble(2, altu);
            ps.setDouble(3, agua);
            ps.setInt(4, cul.getIDproceso());
            ps.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos aqui " + ex);
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        return true;
    }

    public boolean UpdateCultivosInactivos(Cultivo cul, int d) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE procesoscultivos\n"
                + "SET Semana = ? WHERE CODIGO_Cultivo = ?";
        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, d);
            ps.setInt(2, cul.getIDproceso());

            ps.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos " + ex);
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        return true;
    }

    public int CargarDatosInicioSemanas(Usuarios usr) {

        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "SELECT max(semana) FROM registrodepartidaporsema WHERE CODIGO_partida = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, usr.getNPartida());
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return 0;
    }

    public boolean VerificarNombre(String Nombre) {

        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "SELECT nombre_usuario FROM usuarios WHERE nombre_usuario = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setNString(1, Nombre);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            if (rs.next()) {
                if (rs.getString(1).equals(Nombre)) {
                    return true;
                } else {
                    return false;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return false;
    }

    public int ObtenerCultivo() {

        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "SELECT max(CODIGO_Cultivo) FROM registrodepartidaporsema;";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return 0;
    }

    public boolean GuardarProgresoCultivo(int CultivoId, int seman, double Altura) {

        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO procesosdecultivos (CODIGO_Cultivo,semanas, altura) VALUES(?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, CultivoId);
            ps.setInt(2, seman);
            ps.setDouble(3, Altura);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos " + ex);
            return false;
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    public Object[][] CargarUsuarios() {
        ResultSet rs = null;
        ResultSet rs2 = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        Connection con = getConexion();
        String sql = "SELECT Nombres,Apellidos,Numero_celular,correo,nombre_usuario, DataDeRegistro FROM usuarios";
        String sql2 = "SELECT count(*)  FROM usuarios";
        try {
            ps = con.prepareStatement(sql);
            ps2 = con.prepareStatement(sql2);
            rs2 = ps2.executeQuery();
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            rs2.next();
            int n = rs2.getInt(1);
            Object[][] ji = new Object[n][6];
            int i = 0;
            while (rs.next()) {
                ji[i][0] = rs.getNString(1);
                ji[i][1] = rs.getNString(2);
                ji[i][2] = rs.getNString(3);
                ji[i][3] = rs.getNString(4);
                ji[i][4] = rs.getNString(5);
                Timestamp timestamp = new Timestamp(rs.getTimestamp(6).getTime());
                ji[i][5] = timestamp.toString();
                i++;
            }
            return ji;
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return null;
    }

    public Object[][] CargarUsuariosRegsitros(String nombre) {
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        Connection con = getConexion();
        String sql = "SELECT IDUsuario from usuarios where nombre_usuario = ?";

        String sql2 = "SELECT Entrada,Salida FROM registrosdeentradassalidas where usuarios_IDUsuario = ?";
        String sql3 = "SELECT count(*) FROM registrosdeentradassalidas where usuarios_IDUsuario = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            rs.next();
            int dato = rs.getInt(1);
            ps2 = con.prepareStatement(sql2);
            ps3 = con.prepareStatement(sql3);
            ps2.setInt(1, dato);
            ps3.setInt(1, dato);
            rs2 = ps2.executeQuery();
            rs3 = ps3.executeQuery();

        } catch (SQLException ex) {

        }
        try {

            rs3.next();
            int n = rs3.getInt(1);
            Object[][] ji = new Object[n][2];
            int i = 0;
            while (rs2.next()) {
                Timestamp timestamp = new Timestamp(rs2.getTimestamp(1).getTime());
                Timestamp timestamp2= new Timestamp(rs2.getTimestamp(2).getTime());
                ji[i][0] = timestamp.toString();
                ji[i][1] = timestamp2.toString();
                i++;
            }
            return ji;
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return null;
    }

    public Object[][] CargarUsuariosCultivos(String nombre) {
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        Connection con = getConexion();
        String sql = "SELECT IDUsuario from usuarios where nombre_usuario = ?";

        String sql2 = "select  cultivos.NombreCultivo,procesoscultivos.Semana, procesoscultivos.AlturaCultivo, \n"
                + "                 procesoscultivos.Agua\n"
                + "                from cultivosvsprocesos , procesoscultivos, cultivos\n"
                + "                WHERE  procesoscultivos.PartidasUsuarios_idPartidasUsuarios = ?\n"
                + "                and cultivosvsprocesos.Cultivos_idCultivos = cultivos.idCultivos\n"
                + "                and cultivosvsprocesos.ProcesosCultivos_CODIGO_Cultivo = procesoscultivos.CODIGO_Cultivo \n"
                + "                group by  cultivosvsprocesos.idCultivoVsProce  order by cultivosvsprocesos.idCultivoVsProce";
        String sql3 = "select  count(*)\n"
                + "                from cultivosvsprocesos , procesoscultivos, cultivos\n"
                + "                WHERE  procesoscultivos.PartidasUsuarios_idPartidasUsuarios = ?\n"
                + "                and cultivosvsprocesos.Cultivos_idCultivos = cultivos.idCultivos\n"
                + "                and cultivosvsprocesos.ProcesosCultivos_CODIGO_Cultivo = procesoscultivos.CODIGO_Cultivo";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            rs.next();
            Usuarios usr = new Usuarios();
            usr.setIDUsuario(rs.getInt(1));
            int dato = IDNumeroPartida(usr);
            ps2 = con.prepareStatement(sql2);
            ps3 = con.prepareStatement(sql3);
            ps2.setInt(1, dato);
            ps3.setInt(1, dato);
            rs2 = ps2.executeQuery();
            rs3 = ps3.executeQuery();

        } catch (SQLException ex) {

        }
        try {

            rs3.next();
            int n = rs3.getInt(1);
            Object[][] ji = new Object[n][5];
            int i = 0;
            while (rs2.next()) {
                ji[i][0] = rs2.getNString(1);
                if (rs2.getInt(2) > 800) {
                    ji[i][1] = rs2.getInt(2) - 900;
                    ji[i][4] = "Inactivo";
                } else {
                    ji[i][1] = rs2.getInt(2);
                    ji[i][4] = "Activo";
                }
                ji[i][2] = rs2.getDouble(3);
                ji[i][3] = rs2.getDouble(4);
                i++;
            }
            return ji;
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return null;
    }

    public Object[][] CargarUsuariosProceso(String nombre) {
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        Connection con = getConexion();
        String sql = "SELECT IDUsuario from usuarios where nombre_usuario = ?";

        String sql2 = "SELECT Cantidad, PrecioTotal,ProducAcum FROM ofertademandaregistros WHERE PartidasUsuarios_idPartidasUsuarios= ?";
        String sql3 = "SELECT count(*) FROM ofertademandaregistros WHERE PartidasUsuarios_idPartidasUsuarios= ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            rs.next();
            Usuarios usr = new Usuarios();
            usr.setIDUsuario(rs.getInt(1));
            int dato = IDNumeroPartida(usr);
            ps2 = con.prepareStatement(sql2);
            ps3 = con.prepareStatement(sql3);
            ps2.setInt(1, dato);
            ps3.setInt(1, dato);
            rs2 = ps2.executeQuery();
            rs3 = ps3.executeQuery();

        } catch (SQLException ex) {

        }
        try {

            rs3.next();
            int n = rs3.getInt(1);
            Object[][] ji = new Object[n][3];
            int i = 0;
            while (rs2.next()) {
                ji[i][0] = rs2.getInt(1);
                ji[i][1] = rs2.getDouble(2);
                ji[i][2] = rs2.getDouble(3);
                i++;
            }
            return ji;
        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return null;
    }

    public boolean RegistrarPrecios(Usuarios usr, int can, int pro, double prec) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "INSERT INTO ofertademandaregistros (PartidasUsuarios_idPartidasUsuarios,Cantidad,ProducAcum,PrecioTotal) \n"
                + "VALUES(?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, usr.getNPartida());
            ps.setInt(2, can);
            ps.setInt(3, pro);
            ps.setDouble(4, prec);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos " + ex);
            return false;
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    public Integer IDNumeroOfertaDeManda(Usuarios usr) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "SELECT max(idOfertaDemandaRegistros)  FROM ofertademandaregistros WHERE PartidasUsuarios_idPartidasUsuarios= ?";

        try {

            ps = con.prepareStatement(sql);
            ps.setInt(1, usr.getNPartida());
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos " + ex);
            //En caso de error devuelve falso
            return 0;
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public boolean ObtenerOfertaDe(Usuarios usr) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "SELECT Cantidad, PrecioTotal FROM ofertademandaregistros WHERE idOfertaDemandaRegistros= ?";

        try {

            ps = con.prepareStatement(sql);
            ps.setInt(1, usr.getNumeroOFERTADEMANADA());
            rs = ps.executeQuery();
            rs.next();
            usr.setCantida(rs.getInt(1));
            usr.setPrecio(rs.getDouble(2));
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos " + ex);
            //En caso de error devuelve falso
            return false;
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public boolean UpdateOfertAdEMANDA(Usuarios usr, int sema, double altu, int agua) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE ofertademandaregistros SET Cantidad = ?, PrecioTotal = ?, "
                + " ProducAcum =? WHERE idOfertaDemandaRegistros = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, sema);
            ps.setDouble(2, altu);
            ps.setDouble(3, agua);
            ps.setInt(4, usr.getNumeroOFERTADEMANADA());
            ps.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos aqui " + ex);
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        return true;
    }
}
