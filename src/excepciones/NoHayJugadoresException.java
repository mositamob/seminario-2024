package excepciones;

public class NoHayJugadoresException extends Exception{
    public NoHayJugadoresException(String errorMessage) {
        super(errorMessage);
    }
}
