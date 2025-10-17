package Logica;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
    List<DataPropuesta> misPropuestasFav;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.nickname);
        hash = 73 * hash + Objects.hashCode(this.email);
        hash = 73 * hash + Objects.hashCode(this.nombre);
        hash = 73 * hash + Objects.hashCode(this.apellido);
        hash = 73 * hash + Objects.hashCode(this.imagen);
        hash = 73 * hash + Objects.hashCode(this.tipo);
        hash = 73 * hash + Objects.hashCode(this.biografia);
        hash = 73 * hash + Objects.hashCode(this.direccion);
        hash = 73 * hash + Objects.hashCode(this.sitioWeb);
        hash = 73 * hash + Objects.hashCode(this.misPropuestas);
        hash = 73 * hash + Objects.hashCode(this.misPropuestasIngresadas);
        hash = 73 * hash + Objects.hashCode(this.meSiguen);
        hash = 73 * hash + Objects.hashCode(this.losSigo);
        hash = 73 * hash + Objects.hashCode(this.ListaAporte);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DataUsuario other = (DataUsuario) obj;
        if (!Objects.equals(this.nickname, other.nickname)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellido, other.apellido)) {
            return false;
        }
        if (!Objects.equals(this.imagen, other.imagen)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.biografia, other.biografia)) {
            return false;
        }
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        if (!Objects.equals(this.sitioWeb, other.sitioWeb)) {
            return false;
        }
        if (!Objects.equals(this.misPropuestas, other.misPropuestas)) {
            return false;
        }
        if (!Objects.equals(this.misPropuestasIngresadas, other.misPropuestasIngresadas)) {
            return false;
        }
        if (!Objects.equals(this.meSiguen, other.meSiguen)) {
            return false;
        }
        if (!Objects.equals(this.losSigo, other.losSigo)) {
            return false;
        }
        return Objects.equals(this.ListaAporte, other.ListaAporte);
    }

    public List<DataPropuesta> getMisPropuestasFav() {
        return misPropuestasFav;
    }

    public void setMisPropuestasFav(List<DataPropuesta> misPropuestasFav) {
        this.misPropuestasFav = misPropuestasFav;
    }
    
    
    
}
