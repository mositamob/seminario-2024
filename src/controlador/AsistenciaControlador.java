package controlador;

import modelo.entidades.*;

import java.util.*;

public class AsistenciaControlador {
    /**
     * Registra la asistencia de todos los jugadores de una división
     * que correspondan a la semana actual de entrenamiento.
     * @param club
     */
    public void registrarAsistencia(Club club) {
        Scanner divisionScan = new Scanner(System.in);
        System.out.println("Ingrese División:");
        String division = divisionScan.next();
        Plantel plantel = club.getPlanteles().get(division);
        System.out.println("División:" + plantel.getDivision().getNombre());
        Date diaEntrenamiento = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(diaEntrenamiento);
        int numeroSemana = calendar.get(Calendar.WEEK_OF_YEAR);

        List<Jugador> jugadores = plantel.getJugadores();
        for (Jugador j : jugadores) {
            Asistencia asistencia = new Asistencia();
            asistencia.setFecha(diaEntrenamiento);
            Scanner asistenciaScan = new Scanner(System.in);
            System.out.println("Nombre:" + j.getNombre() + " " + j.getApellido());
            System.out.println("Ingrese opción: 1 [Presente] - 0 [Ausente]");
            int opcion = asistenciaScan.nextInt();
            if (opcion == 0) {
                asistencia.setPresente(false);
            } else if (opcion == 1) {
                asistencia.setPresente(true);
            }
            String key = numeroSemana+"-"+ diaEntrenamiento;
            j.getAsistencia().put(key, asistencia);
        }

        for (Jugador jugador : jugadores) {
            Map<String, Asistencia> asistenciaMap = jugador.getAsistencia();
            Asistencia registro = asistenciaMap.get(numeroSemana+"-"+diaEntrenamiento);
            System.out.println("Nombre:" + jugador.getNombre() + " " + jugador.getApellido() + " " + (registro.isPresente() ? "- PRESENTE" : "- AUSENTE"));
        }
        System.out.println("-------------------------------------------");
    }
}
