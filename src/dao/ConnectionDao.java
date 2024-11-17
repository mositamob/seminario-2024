package dao;



import modelo.entidades.Asistencia;
import modelo.entidades.Entrenador;
import modelo.entidades.Jugador;
import modelo.entidades.Plantel;

import java.sql.*;
import java.util.List;


public interface ConnectionDao {
    Connection getConnection();
    List<Jugador> getJugadores(Connection connection, int idDivision);
    Plantel getPlantel(Connection connection, int idDivision);
    List<Jugador> getJugadoresAsistencia(Connection connection, int idDivision);
    void addJugador(Connection connection, Jugador jugador);
    void addEntrenador(Connection connection, Entrenador entrenador);
    void deleteJugador(Connection connection, String dni);
    void updateAsistencia(Connection connection, Asistencia asistencia, String key, String dni);
    void updatePlantel(Connection connection, Plantel plantel);
    void updateJugadoresPlantel(Connection connection, String dni, int idPlantel);
    void updateJugadorIdPlantel(Connection connection, int idPlantel, String dni);
    void deleteJugadorIdPlantel(Connection connection, int idPlantel, String dni);

}
