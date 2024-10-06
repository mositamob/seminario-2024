package modelo.entidades;


import java.util.Date;

public class Entrenador extends Persona{
    private int añosExperiencia;
    private boolean isDocente;
    private boolean isPreparador;

    private Division division;

    public Entrenador() {
        super();
    }

    public Entrenador(String dni, String nombre, String apellido, Date fechaNacimiento) {
        super(dni, nombre, apellido, fechaNacimiento);
    }

    public Entrenador(int añosExperiencia, boolean isDocente,boolean isPreparador, String dni, String nombre, String apellido, Date fechaNacimiento, Division division) {
        super(dni, nombre, apellido, fechaNacimiento);
        this.añosExperiencia = añosExperiencia;
        this.isDocente = isDocente;
        this.division = division;
        this.isPreparador = isPreparador;

    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public int getAñosExperiencia() {
        return añosExperiencia;
    }

    public void setAñosExperiencia(int añosExperiencia) {
        this.añosExperiencia = añosExperiencia;
    }

    public boolean isDocente() {
        return isDocente;
    }

    public void setDocente(boolean docente) {
        isDocente = docente;
    }

    public boolean isPreparador() {
        return isPreparador;
    }

    public void setPreparador(boolean preparador) {
        isPreparador = preparador;
    }

    @Override
    public String mostrarDatosPersonales() {
        return "Nombre: " + this.getNombre() + " " + this.getApellido() + " - Rol: " + (this.isPreparador() ? "Preparador Físico" : "Director Técnico"+  "- Division: "+ this.getDivision().getNombre()+" - DNI : " + this.getDni() );
    }

}
