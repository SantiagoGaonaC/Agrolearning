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
public class TipoUsuarios {
    private int idEstado;
    private String NombreEstado;

    public TipoUsuarios(int idEstado, String NombreEstado) {
        this.idEstado = idEstado;
        this.NombreEstado = NombreEstado;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreEstado() {
        return NombreEstado;
    }

    public void setNombreEstado(String NombreEstado) {
        this.NombreEstado = NombreEstado;
    }
}
