package entidades;

public class Asistencia {
    private String fecha;
    private boolean isPresente;

    public Asistencia() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isPresente() {
        return isPresente;
    }

    public void setPresente(boolean presente) {
        isPresente = presente;
    }
}
