package Logica;

public class DataCategoria {
    private String nombre;
    private String nombreCatPadre;

    public DataCategoria() {
    }

    public DataCategoria(String nombre, String nombreCatPadre) {
        this.nombre = nombre;
        this.nombreCatPadre = nombreCatPadre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCatPadre() {
        return nombreCatPadre;
    }

    public void setNombreCatPadre(String nombreCatPadre) {
        this.nombreCatPadre = nombreCatPadre;
    }

}