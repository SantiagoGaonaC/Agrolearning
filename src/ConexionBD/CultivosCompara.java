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
public class CultivosCompara {
    private String NombreCultivo;
    private int IdCultivo;
    private int semanaMax;
    private double AlturAmAXIam;
    private double tasacCrsi;
    private int cantidad;
    private int Estado;
    private double Precio;

    public CultivosCompara(String NombreCultivo, int IdCultivo, int semanaMax, double AlturAmAXIam, double tasacCrsi, int cantidad, double pre) {
        this.NombreCultivo = NombreCultivo;
        this.IdCultivo = IdCultivo;
        this.semanaMax = semanaMax;
        this.AlturAmAXIam = AlturAmAXIam;
        this.tasacCrsi = tasacCrsi;
        this.cantidad = cantidad;
        this.Precio = pre;
    }

    public CultivosCompara() {
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
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

    public int getSemanaMax() {
        return semanaMax;
    }

    public void setSemanaMax(int semanaMax) {
        this.semanaMax = semanaMax;
    }

    public double getAlturAmAXIam() {
        return AlturAmAXIam;
    }

    public void setAlturAmAXIam(double AlturAmAXIam) {
        this.AlturAmAXIam = AlturAmAXIam;
    }

    public double getTasacCrsi() {
        return tasacCrsi;
    }

    public void setTasacCrsi(double tasacCrsi) {
        this.tasacCrsi = tasacCrsi;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
    
}
