package Logica;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Usuario")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public class Usuario implements Serializable {
    @Id
    String nickname;
    @Basic(optional = false)
    String email;
    String nombre;
    String apellido;
    LocalDate fecNac;
    String imagen = "";
    @OneToMany
    @JoinTable(name = "UsuarioSeguidos", joinColumns = @JoinColumn(name = "nickSeguidor"), inverseJoinColumns = @JoinColumn(name = "nickSeguido"))
    List<Usuario> misSeguidos = new ArrayList<>();

    public Usuario() {
    }

    
    public Usuario(String nickname, String email, String nombre, String apellido, LocalDate fecNac, String imagen) {
        this.nickname = nickname;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecNac = fecNac;
        this.imagen = imagen;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFecNac() {
        return fecNac;
    }

    public void setFecNac(LocalDate fecNac) {
        this.fecNac = fecNac;
    }
    
    public String getImagen(){
        return imagen;
    }
    
    public int seguirUsuario(Usuario nick){
        for(Usuario u : this.misSeguidos){
            if(u.getNickname().equals(nick.getNickname())){
                return 0; //error: ya sigue al usuario nick
            }
        }
        this.misSeguidos.add(nick);
        return 1;
    }
    
    public int dejarDeSeguir(Usuario nick){
        for(Usuario u : this.misSeguidos){
            if(u.getNickname().equals(nick.getNickname())){
                this.misSeguidos.remove(u);
                return 1;
            }
        }
        return 0; //error 0: no se encuentra
    }
    
    public List<String> getSeguidos(){
        List<String> listaSeguidos = new ArrayList<>();
        for(Usuario u : this.misSeguidos){
            listaSeguidos.add(u.getNickname());
        }
        return listaSeguidos;
    }
}
