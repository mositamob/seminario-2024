package modelo.entidades;


import modelo.excepciones.NoHayJugadoresException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Club {
    private String nombre;
    private String fechaInauguración;
    private Map<String, Plantel> planteles = new HashMap<>() {
        {
            put("cuarta", crearPlantel("cuarta"));
            put("quinta", crearPlantel("quinta"));
            put("sexta", crearPlantel("sexta"));
            put("septima", crearPlantel("septima"));
            put("octava", crearPlantel("octava"));
            put("novena", crearPlantel("novena"));
            put("decima", crearPlantel("decima"));
            put("onceava", crearPlantel("onceava"));
            put("doceava", crearPlantel("doceava"));
        }

    };


    public Club() {
    }

    public Club(String nombre, String fechaInauguración) {
        this.nombre = nombre;
        this.fechaInauguración = fechaInauguración;

    }

    public Plantel crearPlantel(String division) {
        Plantel plantel = new Plantel();
        plantel.setDivision(new Division(division));
        plantel.setJugadores(new ArrayList<>());
        return plantel;
    }


    public void validateExistenJugadores(List<Jugador> jugadores) throws NoHayJugadoresException {
        if (jugadores.size() == 0) {
            throw new NoHayJugadoresException("No existen jugadores cargados en la division seleccionada");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaInauguración() {
        return fechaInauguración;
    }

    public void setFechaInauguración(String fechaInauguración) {
        this.fechaInauguración = fechaInauguración;
    }

    public Map<String, Plantel> getPlanteles() {
        return this.planteles;
    }
}
