package dao;

import modelo.entidades.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConnectionDaoImpl implements ConnectionDao {
    private List<Jugador> jugadores = new ArrayList<>();

    public Connection getConnection() {
        Connection conexion;
        String user = "root";
        String pass = "rootroot";
        String host = "jdbc:mysql://localhost:3306/club-futbol";
        String bd = "club";
        System.out.println("Conectando a la Base de Datos");
        try {
            conexion = DriverManager.getConnection(host, user, pass);
            System.out.println("Conexión exitosa");
        } catch (SQLException e) {
            System.out.println("Error al conectarse a la BD");
            throw new RuntimeException(e);
        }
        return conexion;
    }


    public List<Jugador> getJugadores(Connection connection, int idDivision) {

        String selectQuery = "SELECT * \n" + "FROM jugadores j\n" + "INNER JOIN  plantel p ON j.id_plantel = p.id_plantel\n" + "INNER JOIN categoria c ON p.id_categoria = c.id_categoria\n" + "INNER JOIN division d ON d.id_division= c.id_division\n" + "INNER JOIN posicion po ON j.id_posicion = po.id_posicion\n" + "WHERE d.id_division = ?";
        PreparedStatement selectStatement;
        try {
            selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setInt(1, idDivision);
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                Jugador j = new Jugador();
                j.setNombre(resultSet.getString("nombre"));
                j.setApellido(resultSet.getString("apellido"));
                j.setAptoFisico(resultSet.getBoolean("apto_fisico"));
                j.setDni(resultSet.getString("dni"));
                j.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));

                Division division = new Division(resultSet.getString("descripcion_division"));
                Categoria categoria = new Categoria(division, resultSet.getString("descripcion_categoria"));
                j.setCategoria(categoria);
                Posicion posicion = Posicion.valueOf(resultSet.getString("descripcion_posicion").toUpperCase());
                j.setPosicion(posicion);

                jugadores.add(j);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return jugadores;
    }

    @Override
    public Plantel getPlantel(Connection connection, int idDivision) {
        List<Jugador> jugadores = new ArrayList<>();

        String selectQuery = "SELECT DISTINCT j.nombre, j.apellido, j.dni, j.apto_fisico,j.fecha_nacimiento, c.id_categoria, j.id_posicion, p.id_plantel, d.descripcion_division, po.descripcion_posicion\n" +
                "FROM `club-futbol`.plantel p\n" +
                "INNER JOIN jugadores j ON p.id_plantel = j.id_plantel\n" +
                "INNER JOIN categoria c ON j.categoria = c.id_categoria\n" +
                "INNER JOIN division d ON d.id_division= c.id_division\n" +
                "INNER JOIN posicion po ON j.id_posicion = po.id_posicion\n" +
                "INNER JOIN jugadores_x_plantel jp ON p.id_plantel = jp.id_plantel\n" +
                "Where d.id_division = ?";
        PreparedStatement selectStatement;

        Plantel plantel = new Plantel();
        ;
        try {

            selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setInt(1, idDivision);
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                Division division = new Division(resultSet.getString("descripcion_division"));
                plantel.setDivision(division);
                plantel.setId(resultSet.getInt("id_plantel"));
                Jugador j = new Jugador();
                j.setNombre(resultSet.getString("nombre"));
                j.setApellido(resultSet.getString("apellido"));
                j.setAptoFisico(resultSet.getBoolean("apto_fisico"));
                j.setDni(resultSet.getString("dni"));
                j.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
                Categoria categoria = new Categoria(division, resultSet.getString("id_categoria"));
                j.setCategoria(categoria);
                Posicion posicion = Posicion.valueOf(resultSet.getString("descripcion_posicion").toUpperCase());
                j.setPosicion(posicion);

                jugadores.add(j);
            }
            plantel.setJugadores(jugadores);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return plantel;
    }

    @Override
    public List<Jugador> getJugadoresAsistencia(Connection connection, int idDivision) {

        String selectQuery = "SELECT a.key_asistencia as key_asistencia,\n" + " a.presente as presente, j.nombre as nombre,\n" + " j.apellido as apellido, po.descripcion_posicion as descripcion_posicion, j.dni as dni\n" + "FROM jugadores j\n" + "INNER JOIN asistencia a ON j.dni = a.dni\n" + "INNER JOIN  plantel p ON j.id_plantel = p.id_plantel\n" + "INNER JOIN categoria c ON p.id_categoria = c.id_categoria\n" + "INNER JOIN division d ON d.id_division= c.id_division\n" + "INNER JOIN posicion po ON j.id_posicion = po.id_posicion\n" + "Where d.id_division = ?";
        PreparedStatement selectStatement;
        try {
            selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setInt(1, idDivision);
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                Jugador j = new Jugador();
                j.setNombre(resultSet.getString("nombre"));
                j.setApellido(resultSet.getString("apellido"));
                j.setDni(resultSet.getString("dni"));
                Asistencia asistencia = new Asistencia();
                asistencia.setPresente(resultSet.getBoolean("presente"));
                j.getAsistencia().put(resultSet.getString("key_asistencia"), asistencia);
                Posicion posicion = Posicion.valueOf(resultSet.getString("descripcion_posicion").toUpperCase());
                j.setPosicion(posicion);

                jugadores.add(j);
            }

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return jugadores;
    }

    @Override
    public void addJugador(Connection connection, Jugador jugador) {
        String sql = "INSERT INTO `club-futbol`.`jugadores` (`dni`,`nombre`, `apellido`, `apto_fisico`, `id_posicion`,`fecha_nacimiento`,`categoria`) " + "VALUES (?, ?, ?, ?,?,?,?);";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            preparedStmt.setString(1, jugador.getDni());
            preparedStmt.setString(2, jugador.getNombre());
            preparedStmt.setString(3, jugador.getApellido());
            preparedStmt.setBoolean(4, jugador.isAptoFisico());
            preparedStmt.setInt(5, jugador.getPosicion().getCodigo());
            preparedStmt.setDate(6, new java.sql.Date(jugador.getFechaNacimiento().getTime()));
            preparedStmt.setString(7, jugador.getCategoria().getNombre());
            preparedStmt.execute();
            System.out.println("se suma un jugador: " + jugador.getNombre() + "," + jugador.getApellido() + "," + jugador.getDni());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addEntrenador(Connection connection, Entrenador entrenador) {
        String sql = "INSERT INTO `club-futbol`.`entrenadores` (`nombre`, `apellido`, `fecha_nacimiento`, `is_preparador`, `años_experiencia`,`division`) VALUES (?, ?, ?, ?, ?,?);";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            preparedStmt.setString(1, entrenador.getNombre());
            preparedStmt.setString(2, entrenador.getApellido());
            java.sql.Date sqlDate = new java.sql.Date(entrenador.getFechaNacimiento().getTime());
            preparedStmt.setDate(3, sqlDate);
            preparedStmt.setBoolean(4, entrenador.isPreparador());
            preparedStmt.setInt(5, entrenador.getAñosExperiencia());
            preparedStmt.setString(6, entrenador.getDivision().getNombre());


            preparedStmt.execute();
            System.out.println("se agrego al entrenador: " + entrenador.getNombre() + " " + entrenador.getApellido());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteJugador(Connection connection, String dni) {
        String sql = "DELETE FROM `club-futbol`.`jugadores` WHERE dni = ?";

        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1, dni);
            // Ejecutar la eliminación
            int filasAfectadas = preparedStmt.executeUpdate();
            // Verificar si se eliminó algún registro
            if (filasAfectadas > 0) {
                System.out.println("Jugador eliminado correctamente.");
            } else {
                System.out.println("No se encontró un jugador con ese dni.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void updateAsistencia(Connection connection, Asistencia asistencia, String key, String dni) {

        String sql = "INSERT INTO `club-futbol`.`asistencia` (`fecha`, `presente`, `key_asistencia`, `dni`) VALUES (?, ?, ?, ?);";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            java.sql.Date fechaSql = new java.sql.Date(asistencia.getFecha().getTime());
            preparedStmt.setDate(1, fechaSql);
            preparedStmt.setBoolean(2, asistencia.isPresente());
            preparedStmt.setString(3, key);
            preparedStmt.setString(4, dni);
            preparedStmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePlantel(Connection connection, Plantel plantel) {
        long idGenerado = 0;
        String sql = "INSERT INTO `club-futbol`.`partido` (`fecha`) VALUES (?);";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (plantel.getEquipoConvocado() != null) {
            Date fechaPartido = plantel.getEquipoConvocado().getPartido().getFechaPartido();
            try {
                preparedStmt.setDate(1, new java.sql.Date(fechaPartido.getTime()));
                int filasAfectadas = preparedStmt.executeUpdate();
                if (filasAfectadas == 1) {
                    // Obtener las claves generadas
                    try (ResultSet generatedKeys = preparedStmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            idGenerado = generatedKeys.getLong(1); // La primera columna de las claves generadas
                            System.out.println("ID generado: " + idGenerado);
                        } else {
                            System.out.println("No se generó ningún ID.");
                        }
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            //update jugadores x partido
            String sqlJP = "INSERT INTO `club-futbol`.`jugadores_x_partido` (`dni`, `id_partido`) VALUES (?,?);";
            PreparedStatement ps;
            try {
                ps = connection.prepareStatement(sqlJP);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                for (Jugador j : plantel.getEquipoConvocado().getJugadores()) {
                    ps.setString(1, j.getDni());
                    ps.setInt(2, (int) idGenerado);
                    ps.execute();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Se actualizo jugadores x partido");
        }

    }

    @Override
    public void updateJugadoresPlantel(Connection connection, String dni, int idPlantel) {
        String sql = "INSERT INTO `club-futbol`.`jugadores_x_plantel` (`dni`, `id_plantel`) VALUES (?, ?);";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            preparedStmt.setString(1, dni);
            preparedStmt.setInt(2, idPlantel);
            preparedStmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateJugadorIdPlantel(Connection connection, int idPlantel, String dni) {
        String sql = "UPDATE `club-futbol`.`jugadores` SET `id_plantel` = ? WHERE `dni` = ?";

        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            preparedStmt.setInt(1, idPlantel);
            preparedStmt.setString(2, dni);
            int filasAfectadas = preparedStmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Jugador actualizado con éxito.");
            } else {
                System.out.println("No se encontró ningún Jugador con ese ID.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteJugadorIdPlantel(Connection connection, int idPlantel, String dni) {
        String sql = "DELETE FROM `club-futbol`.`jugadores_x_plantel` WHERE `dni` = ? and `id_plantel` = ?";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1, dni);
            preparedStmt.setInt(1, idPlantel);
            // Ejecutar la eliminación
            int filasAfectadas = preparedStmt.executeUpdate();
            // Verificar si se eliminó algún registro
            if (filasAfectadas > 0) {
                System.out.println("Jugador eliminado correctamente.");
            } else {
                System.out.println("No se encontró un jugador con ese dni.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
