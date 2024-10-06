package modelo.entidades;


import java.util.ArrayList;
import java.util.List;

public class Equipo {

    private List<Jugador> jugadores = new ArrayList<>();
    private Partido partido;

    public Equipo() {
    }

    public List<Jugador> getJugadores() {
        return jugadores ;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }


    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

}
