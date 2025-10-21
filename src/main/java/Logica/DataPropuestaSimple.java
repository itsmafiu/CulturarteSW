/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author Luiano
 */
public class DataPropuestaSimple {
    
    private String titulo;
    private String descripcion;
    private String lugar;
    private EnumEstado enumEstado;

    public DataPropuestaSimple() {
    }   
    
    public DataPropuestaSimple(String titulo, String descripcion, String lugar,EnumEstado enumEstado) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.enumEstado = enumEstado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public EnumEstado getEnumEstado() {
        return enumEstado;
    }

    public void setEnumEstado(EnumEstado enumEstado) {
        this.enumEstado = enumEstado;
    }
    
    
}
