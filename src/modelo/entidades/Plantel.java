package modelo.entidades;

import java.util.ArrayList;
import java.util.List;

public class Plantel {
    private Entrenador directorTecnico;
    private Entrenador preparadorFisico;
    private List<Jugador> jugadores = new ArrayList<>();
    private Division division;
    private Equipo equipoConvocado;

    public Plantel() {
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Entrenador getDirectorTecnico() {
        return directorTecnico;
    }

    public void setDirectorTecnico(Entrenador directorTecnico) {
        this.directorTecnico = directorTecnico;
    }

    public Entrenador getPreparadorFisico() {
        return preparadorFisico;
    }

    public void setPreparadorFisico(Entrenador preparadorFisico) {
        this.preparadorFisico = preparadorFisico;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Equipo getEquipoConvocado() {
        return equipoConvocado;
    }

    public void setEquipoConvocado(Equipo equipoConvocado) {
        this.equipoConvocado = equipoConvocado;
    }
}
