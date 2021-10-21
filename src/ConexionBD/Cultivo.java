/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionBD;

/**
 *
 * @author Acer
 */
public class Cultivo {
    
    private String NombreCultivo;
    private int IdCultivo;
    private int IDDeCultivoVSproceso;
    private int IDproceso;
    private double Altura;
    private int Semana;
    private double Agua;
    private double tasa;
    private double AlturAmAXIam;
    private int cantidad;
    private boolean AguaRegistrada;
    private boolean Borrar;
    

    public Cultivo(String NombreCultivo, int IdCultivo, int IDproceso, double Altura, int Semana, double Agua, int divs) {
        this.NombreCultivo = NombreCultivo;
        this.IdCultivo = IdCultivo;
        this.IDproceso = IDproceso;
        this.Altura = Altura;
        this.Semana = Semana;
        this.Agua = Agua;
        this.IDDeCultivoVSproceso = divs;
    }
    public Cultivo(String NombreCultivo, int IdCultivo, int IDproceso, double Altura, int Semana, double Agua) {
        this.NombreCultivo = NombreCultivo;
        this.IdCultivo = IdCultivo;
        this.IDproceso = IDproceso;
        this.Altura = Altura;
        this.Semana = Semana;
        this.Agua = Agua;
    }

    public boolean getAguaRegistrada() {
        return AguaRegistrada;
    }

    public void setAguaRegistrada(boolean AguaRegistrada) {
        this.AguaRegistrada = AguaRegistrada;
    }

    public boolean getBorrar() {
        return Borrar;
    }

    public void setBorrar(boolean Borrar) {
        this.Borrar = Borrar;
    }

    
    
    public double getAlturAmAXIam() {
        return AlturAmAXIam;
    }

    public void setAlturAmAXIam(double AlturAmAXIam) {
        this.AlturAmAXIam = AlturAmAXIam;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
    public double gettasa() {
        return tasa;
    }

    public void settasa(double AlturaMaxi) {
        this.tasa = AlturaMaxi;
    }
   
    
    

    public String getNombreCultivo() {
        return NombreCultivo;
    }

    public void setNombreCultivo(String NombreCultivo) {
        this.NombreCultivo = NombreCultivo;
    }

    public int getIdCultivo() {
        return IdCultivo;
    }

    public void setIdCultivo(int IdCultivo) {
        this.IdCultivo = IdCultivo;
    }

    public int getIDproceso() {
        return IDproceso;
    }

    public void setIDproceso(int IDproceso) {
        this.IDproceso = IDproceso;
    }

    public double getAltura() {
        return Altura;
    }

    public void setAltura(double Altura) {
        this.Altura = Altura;
    }

    public int getSemana() {
        return Semana;
    }

    public void setSemana(int Semana) {
        this.Semana = Semana;
    }

    public double getAgua() {
        return Agua;
    }

    public void setAgua(double Agua) {
        this.Agua = Agua;
    }

   
    
}
