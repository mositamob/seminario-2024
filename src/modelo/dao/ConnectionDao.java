package modelo.dao;



import modelo.entidades.Asistencia;
import modelo.entidades.Entrenador;
import modelo.entidades.Jugador;
import modelo.entidades.Plantel;

import java.sql.*;
import java.util.List;


public interface ConnectionDao {
    Connection getConnection();
    List<Jugador> getJugadores(ConnectionDao connection, int idDivision);
    Plantel getPlantel(ConnectionDao connection, int idDivision);
    List<Jugador> getJugadoresAsistencia(ConnectionDao connection, int idDivision);
    void addJugador(ConnectionDao connection, Jugador jugador);
    void addEntrenador(ConnectionDao connection, Entrenador entrenador);
    void deleteJugador(ConnectionDao connection, String dni);
    void updateAsistencia(ConnectionDao connection, Asistencia asistencia, String key, String dni);
    void updatePlantel(ConnectionDao connection, Plantel plantel);
    void updateJugadoresPlantel(ConnectionDao connection, String dni, int idPlantel);
    void updateJugadorIdPlantel(ConnectionDao connection, int idPlantel, String dni);
    void deleteJugadorIdPlantel(ConnectionDao connection, int idPlantel, String dni);

}
