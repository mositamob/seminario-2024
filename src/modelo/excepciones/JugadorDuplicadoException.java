package modelo.excepciones;

public class JugadorDuplicadoException extends Exception{
    public JugadorDuplicadoException(String errorMessage) {
        super(errorMessage);
    }
}
