package modelo.entidades;

import java.util.Date;

public class Asistencia {
    private Date fecha;
    private boolean isPresente;

    public Asistencia() {
    }

    public Asistencia(boolean isPresente, Date fecha) {
        this.isPresente = isPresente;
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isPresente() {
        return isPresente;
    }

    public void setPresente(boolean presente) {
        isPresente = presente;
    }
}
