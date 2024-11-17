package modelo.entidades;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Jugador extends Persona implements Comparable<Jugador> {
    private Categoria categoria;
    private Posicion posicion;
    private boolean aptoFisico;
    private Map<String, Asistencia> asistencia = new HashMap<>();
    private int totalPresente = 0;
    private int idPlantel;

    public Jugador() {
    }

    public Jugador(String dni, String nombre, String apellido, Date fechaNacimiento, boolean aptoFisico) {
        super(dni, nombre, apellido, fechaNacimiento);
        this.aptoFisico = aptoFisico;
    }

    public Jugador(String dni, String nombre, String apellido, Date fechaNacimiento, Categoria categoria, Posicion posicion, boolean aptoFisico) {
        super(dni, nombre, apellido, fechaNacimiento);
        this.categoria = categoria;
        this.posicion = posicion;
        this.aptoFisico = aptoFisico;
    }

    public Map<String, Asistencia> getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Map<String, Asistencia> asistencia) {
        this.asistencia = asistencia;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public boolean isAptoFisico() {
        return aptoFisico;
    }

    public void setAptoFisico(boolean aptoFisico) {
        this.aptoFisico = aptoFisico;
    }

    public int getTotalPresente() {
        return totalPresente;
    }

    public void setTotalPresente() {
        this.totalPresente ++;
    }

    public int getIdPlantel() {
        return idPlantel;
    }

    public void setIdPlantel(int idPlantel) {
        this.idPlantel = idPlantel;
    }

    public void calcularClasesPresente(String semanaActual) {
        int total = 0;
        for (var entry : asistencia.entrySet()) {
            String semana = entry.getKey().split("_")[0];
            if (semana.equalsIgnoreCase(semanaActual) && entry.getValue().isPresente()) {
                total++;
            }
        }
        this.totalPresente = total;
    }

    @Override
    public String mostrarDatosPersonales() {
        return "\nCategoria: " + this.getCategoria().getNombre() + "- Division: " + this.getCategoria().getDivision().getNombre()
                + "- Posición: " + this.getPosicion().name()+ "\n DNI : " + this.getDni()
                + "- Nombre: " + this.getNombre() + " " + this.getApellido() + " - Apto Fisico: "
                + (this.isAptoFisico() ? "si" : "no");
    }

    @Override
    public int compareTo(Jugador j) {
        if (this.totalPresente > j.getTotalPresente()) {
            return -1; //el total es mayor
        }
        if (this.totalPresente < j.getTotalPresente()) {
            return 1;//el total es menor
        }
        return 0;//los totales son iguales
    }
}
