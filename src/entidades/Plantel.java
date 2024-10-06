package entidades;

import java.util.List;

public class Plantel {
    private Entrenador directorTecnico;
    private Entrenador preparadorFisico;
    private List<Jugador> jugadores;
    private Categoria categoria;
    private Equipo equipoConvocado;

    public Plantel() {
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Equipo getEquipoConvocado() {
        return equipoConvocado;
    }

    public void setEquipoConvocado(Equipo equipoConvocado) {
        this.equipoConvocado = equipoConvocado;
    }
}
