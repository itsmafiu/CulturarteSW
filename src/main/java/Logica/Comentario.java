/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.io.Serializable;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Luiano
 */
@Entity
@Table(name="Comentario")
public class Comentario implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "titulo")
    private Propuesta miPropuesta;
    @ManyToOne
    @JoinColumn(name = "nickname")
    private Colaborador miColaborador;
    private String texto;
    private LocalDateTime fecha;

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Comentario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Propuesta getMiPropuesta() {
        return miPropuesta;
    }

    public void setMiPropuesta(Propuesta miPropuesta) {
        this.miPropuesta = miPropuesta;
    }

    public Colaborador getMiColaborador() {
        return miColaborador;
    }

    public void setMiColaborador(Colaborador miColaborador) {
        this.miColaborador = miColaborador;
    }
    
    
    
    public Comentario(Propuesta miProp, Colaborador miColab, String texto, LocalDateTime fecha) {
        this.miPropuesta = miProp;
        this.miColaborador = miColab;
        this.texto = texto;
        this.fecha = fecha;
    }

    public Propuesta getMiProp() {
        return miPropuesta;
    }

    public Colaborador getMiColab() {
        return miColaborador;
    }

    public String getTexto() {
        return texto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
    
    public String getNickColaborador(){
        return this.miColaborador.getNickname();
    }
    
    public String getImagenColaborador(){
        return this.miColaborador.getImagenWeb();
    }
}
