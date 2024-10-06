package entidades;

import java.util.List;

public class Resultado {
    private List<Gol> golesClub;
    private List<Gol> golesRivales;
    private int resultado;

    /*
     * Calcula empate si los resultados son iguales
     * */
    public boolean isEmpate() {
        return false;
    }

    public boolean isResultadoGanador() {
        return false;
    }

    public int getCantidadGolesClub() {
        return 0;
    }

    public String obtenerJugadorGoleador() {
        return "";
    }

    public List<Gol> getGolesClub() {
        return golesClub;
    }

    public void setGolesClub(List<Gol> golesClub) {
        this.golesClub = golesClub;
    }

    public List<Gol> getGolesRivales() {
        return golesRivales;
    }

    public void setGolesRivales(List<Gol> golesRivales) {
        this.golesRivales = golesRivales;
    }
}
