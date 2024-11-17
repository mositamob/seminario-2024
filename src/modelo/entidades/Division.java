package modelo.entidades;

import java.util.HashMap;
import java.util.Map;

public class Division {
    private String nombre;
    public static Map<String, Integer> divisiones = new HashMap<>() {
        {
            put("cuarta", 4);
            put("quinta", 5);
            put("sexta", 6);
            put("septima", 7);
            put("octava", 8);
            put("novena", 9);
            put("decima", 10);
            put("onceava", 11);
            put("doceava", 12);
        }
    };

    public Division(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static Map<String, Integer> getDivisiones() {
        return divisiones;
    }

    public static void setDivisiones(Map<String, Integer> divisiones) {
        Division.divisiones = divisiones;
    }
}
