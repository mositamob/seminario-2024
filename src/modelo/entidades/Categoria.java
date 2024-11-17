package modelo.entidades;


public class Categoria {
    private Division division;
    private String nombre;

    public Categoria() {
    }

    public Categoria(Division division, String nombre) {
        this.division = division;
        this.nombre = nombre;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
