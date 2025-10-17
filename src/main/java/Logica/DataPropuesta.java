/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nahud
 */
public class DataPropuesta {
    
    String titulo;
    String imagen;
    Estado estadoActual;
    Proponente p;
    String desc;
    String lugar;
    double $entrada;
    double $necesaria;
    double $alcanzada;
    LocalDate fecha;
    LocalDate fechaPubli;
    LocalDateTime fechaLimit; 
    EnumRetorno posibleRetorno;
    String categoria;
    String nickProponenteDe;
    private List<Aporte> misAportes = new ArrayList<>();
    
    
    public DataPropuesta(String titulo, String imagen, Estado estadoActual, Proponente p, String descrip, String lugar, double entrada, double necesaria,double alcanzada,LocalDate fecha, LocalDate fechaP, LocalDateTime fechaL , EnumRetorno retorno, String categoria, List<Aporte> misAportes ){
        this.$alcanzada = alcanzada;
        this.titulo = titulo;
        this.imagen = imagen;
        this.estadoActual = estadoActual;
        this.p = p;
        this.desc = descrip;
        this.lugar = lugar;
        this.$entrada = entrada;
        this.$necesaria = necesaria;
        this.fecha = fecha;
        this.fechaPubli = fechaP;
        this.fechaLimit = fechaL;
        this.posibleRetorno = retorno;
        this.categoria = categoria;
        this.misAportes = misAportes;
        this.nickProponenteDe = p.getNickname();
    }
    
    public DataPropuesta(double alcanzada, String titulo, Estado estadoActual, String lugar){
        this.$alcanzada = alcanzada;
        this.titulo = titulo;
        this.estadoActual = estadoActual;
        this.lugar = lugar;
    }
    public DataPropuesta(double alcanzada, String titulo, Estado estadoActual, String lugar,String nickProponente){
        this.$alcanzada = alcanzada;
        this.titulo = titulo;
        this.estadoActual = estadoActual;
        this.lugar = lugar;
        this.nickProponenteDe = nickProponente;
    }
    
    public String getNickProponenteDe(){
        return this.nickProponenteDe;
    }
    public Double getAlcanzada(){
        return this.$alcanzada;
    }
    
    public String getTitulo(){
        return this.titulo;
    }
    
    public String getImagen(){
        return this.imagen;
    }
    
    public Estado getEstadoActual(){
        return this.estadoActual;
    }
    
    public Proponente getProponente(){
        return this.p;
    }
    
    public String getDescripcion(){
        return this.desc;
    }
    
    public String getLugar(){
        return this.lugar;
    }
    
    public Double getEntrada(){
        return this.$entrada;
    }
    
    public Double getNecesaria(){
        return this.$necesaria;
    }
    
    public EnumRetorno getRetorno(){
        return this.posibleRetorno;
    }
    
    public LocalDate getFechaARealizar(){
        return this.fechaPubli;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
     public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Aporte> getMisAportes() {
        return misAportes;
    }
     public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setFechaPubli(LocalDate fechaPubli) {
        this.fechaPubli = fechaPubli;
    }

    public LocalDateTime getFechaLimit() {
        return fechaLimit;
    }

    public void setFechaLimit(LocalDateTime fechaLimit) {
        this.fechaLimit = fechaLimit;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    
    
   
}
