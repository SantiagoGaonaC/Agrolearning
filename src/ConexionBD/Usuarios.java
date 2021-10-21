
package ConexionBD;

public class Usuarios {
    //Campos que tiene nuestra base de datos    
    private int IDUsuario;
    private int Cantida;
    private double Precio;
    private String Nombres;
    private String Apellidos;
    private String Nombre_finca;
    private String Numero_celular;
    private String correo;
    private String nombre_usuario;
    private String contraseña;
    private String ultima_sesion;
    private Integer NPartida;
    private int EstadoUsu;
    private int NumeroOFERTADEMANADA;
    private int id_tipo;
    private int SemanasJuga;

    public int getCantida() {
        return Cantida;
    }

    public void setCantida(int Cantida) {
        this.Cantida = Cantida;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    
    public int getNumeroOFERTADEMANADA() {
        return NumeroOFERTADEMANADA;
    }

    public void setNumeroOFERTADEMANADA(int NumeroOFERTADEMANADA) {
        this.NumeroOFERTADEMANADA = NumeroOFERTADEMANADA;
    }

    
    public int getSemanasJuga() {
        return SemanasJuga;
    }

    public void setSemanasJuga(int SemanasJuga) {
        this.SemanasJuga = SemanasJuga;
    }

    public int getIDUsuario() {
        return IDUsuario;
    }

    public int getEstadoUsu() {
        return EstadoUsu;
    }

    public void setEstadoUsu(int EstadoUsu) {
        this.EstadoUsu = EstadoUsu;
    }

    public void setIDUsuario(int IDUsuario) {
        this.IDUsuario = IDUsuario;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getNombre_finca() {
        return Nombre_finca;
    }

    public void setNombre_finca(String Nombre_finca) {
        this.Nombre_finca = Nombre_finca;
    }

    public String getNumero_celular() {
        return Numero_celular;
    }

    public void setNumero_celular(String Numero_celular) {
        this.Numero_celular = Numero_celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getUltima_sesion() {
        return ultima_sesion;
    }

    public void setUltima_sesion(String ultima_sesion) {
        this.ultima_sesion = ultima_sesion;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public Integer getNPartida() {
        return NPartida;
    }

    public void setNPartida(Integer NPartida) {
        this.NPartida = NPartida;
    }

   
    
}
