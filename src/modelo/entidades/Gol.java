package modelo.entidades;

public class Gol {
    private Jugador jugador;
    private int minutoDeGol;
    private boolean isEnContra;

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public int getMinutoDeGol() {
        return minutoDeGol;
    }

    public void setMinutoDeGol(int minutoDeGol) {
        this.minutoDeGol = minutoDeGol;
    }

    public boolean isEnContra() {
        return isEnContra;
    }

    public void setEnContra(boolean enContra) {
        isEnContra = enContra;
    }
}
