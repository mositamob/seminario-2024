package excepciones;

public class InvalidAñoCategoriaException extends Exception{
    public InvalidAñoCategoriaException(String errorMessage) {
        super(errorMessage);
    }
}
