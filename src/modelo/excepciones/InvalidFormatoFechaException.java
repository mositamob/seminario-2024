package modelo.excepciones;

public class InvalidFormatoFechaException extends Exception{
    public InvalidFormatoFechaException(String errorMessage) {
        super(errorMessage);
    }
}
