package controlador;

import modelo.dao.ConnectionDao;
import modelo.dao.ConnectionDaoImpl;
import modelo.entidades.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class AsistenciaControlador extends PlantelControlador {
    /**
     * Registra la asistencia de todos los jugadores de una división
     * que correspondan a la semana actual de entrenamiento.
     *
     * @param club
     */
    public void registrarAsistencia(Club club) {
        Scanner divisionScan = new Scanner(System.in);
        System.out.println("Ingrese División:");
        String division = divisionScan.next();
        Integer divisionSeleccionada = Division.getDivisiones().get(division);
        ConnectionDao con = new ConnectionDaoImpl();
        Connection conexion = con.getConnection();
        List<Jugador> jugadores = con.getJugadores(conexion, divisionSeleccionada);

        System.out.println("División:" + divisionSeleccionada);
        Date diaEntrenamiento = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(diaEntrenamiento);
        int numeroSemana = calendar.get(Calendar.WEEK_OF_YEAR);
        int anio = calendar.get(Calendar.YEAR);

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
            String key = numeroSemana + "_" + anio + "-" + diaEntrenamiento;
            j.getAsistencia().put(key, asistencia);
            con.updateAsistencia(conexion, asistencia, key, j.getDni());
        }
        try {
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Jugador jugador : jugadores) {
            Map<String, Asistencia> asistenciaMap = jugador.getAsistencia();
            Asistencia registro = asistenciaMap.get(numeroSemana + "-" + diaEntrenamiento);
            if (registro != null) {
                System.out.println("Nombre:" + jugador.getNombre() + " " + jugador.getApellido() + " " + (registro.isPresente() ? "- PRESENTE" : "- AUSENTE"));
            }
        }
        System.out.println("-------------------------------------------");
    }
}
