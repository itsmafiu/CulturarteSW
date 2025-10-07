package Logica;

public class DataUsuario {
    String nickname;
    String email;
    String nombre;
    String apellido;
    String imagen = "";
    String tipo;
    String biografia = "";

    public DataUsuario() {
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
    
    
    
}
