import vista.Menu;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        try {
            menu.mostrarOpciones();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}