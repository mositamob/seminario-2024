package modelo.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Partido {
    private Date fechaPartido;
    private Resultado resultado;
    private String rival;
     private Torneo torneo;

    public Partido() {
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public String getRival() {
        return rival;
    }

    public void setRival(String rival) {
        this.rival = rival;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public List<Jugador> listarJugadoresdePartidos(){
        return new ArrayList<>();
    };
    public Date getFechaPartido() {
        return fechaPartido;
    }

    public void setFechaPartido(Date fechaPartido) {
        this.fechaPartido = fechaPartido;
    }

}
