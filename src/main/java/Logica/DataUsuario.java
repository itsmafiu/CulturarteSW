package Logica;

import java.util.HashMap;
import java.util.List;

public class DataUsuario {
    String nickname;
    String email;
    String nombre;
    String apellido;
    String imagen = "";
    String tipo;
    String biografia = "";
    String direccion;
    String sitioWeb = "";
    List<DataPropuesta> misPropuestas;
    List<DataPropuesta> misPropuestasIngresadas;
    List<DataUsuario> meSiguen;
    List<DataUsuario> losSigo;
    HashMap<String,DataAporte> ListaAporte;
    public DataUsuario() {
    }

    public DataUsuario(String nickname, String nombre, String apellido, String tipo, List<DataPropuesta> misPropuestas, List<DataUsuario> meSiguen, List<DataUsuario> losSigo) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo = tipo;
        this.misPropuestas = misPropuestas;
        this.meSiguen = meSiguen;
        this.losSigo = losSigo;
    }
    
    public DataUsuario(String nickname, String email, String nombre, String apellido, String tipo) {
        this.nickname = nickname;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo = tipo;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getImagen() {
        return imagen;
    }

    public String getTipo() {
        return tipo;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public void setMisPropuestas(List<DataPropuesta> misPropuestas) {
        this.misPropuestas = misPropuestas;
    }

    public void setMeSiguen(List<DataUsuario> meSiguen) {
        this.meSiguen = meSiguen;
    }

    public void setLosSigo(List<DataUsuario> losSigo) {
        this.losSigo = losSigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public List<DataPropuesta> getMisPropuestas() {
        return misPropuestas;
    }

    public List<DataUsuario> getMeSiguen() {
        return meSiguen;
    }

    public List<DataUsuario> getLosSigo() {
        return losSigo;
    }

    public void setMisPropuestasIngresadas(List<DataPropuesta> misPropuestasIngresadas) {
        this.misPropuestasIngresadas = misPropuestasIngresadas;
    }

    public List<DataPropuesta> getMisPropuestasIngresadas() {
        return misPropuestasIngresadas;
    }

    public void setListaAporte(List<DataAporte> ListaAporte) {
        HashMap<String, DataAporte> DiccAporte = new HashMap<String, DataAporte>();
        for (DataAporte aporte : ListaAporte){
            DiccAporte.put(aporte.getMiPropuesta(), aporte);
            
        }
        this.ListaAporte = DiccAporte;
    }

    public HashMap<String,DataAporte> getListaAporte() {
        return ListaAporte;
    }
    
    
    
}
