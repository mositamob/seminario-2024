package controlador;

import modelo.dao.ConnectionDao;
import modelo.dao.ConnectionDaoImpl;
import modelo.entidades.*;
import excepciones.JugadorDuplicadoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.next;


public class PlantelControlador {
    public static Map<String, Division> categoriasDivision = new HashMap<>() {
        {
            put("2007", new Division("cuarta"));
            put("2008", new Division("quinta"));
            put("2009", new Division("sexta"));
            put("2010", new Division("septima"));
            put("2011", new Division("octava"));
            put("2012", new Division("novena"));
            put("2013", new Division("decima"));
            put("2014", new Division("onceava"));
            put("2015", new Division("doceava"));
        }

    };

    public PlantelControlador() {
    }

    /**
     * Asigna un nuevo jugador a una divisón de los planteles del club.
     *
     * @param jugador
     * @param club
     */
    public void asignarJugadorAPlantel(Jugador jugador, Club club) {
        ConnectionDao con = new ConnectionDaoImpl();
        Connection conexion = con.getConnection();
        System.out.println("Confirma Alta Jugador? Si - No ");
        Scanner siNoScan = new Scanner(System.in);
        String opcionSiNo = siNoScan.next();

        if (opcionSiNo.equalsIgnoreCase("si")) {
            Integer d = Division.divisiones.get(jugador.getCategoria().getDivision().getNombre());
            Plantel plantel = con.getPlantel(conexion, d);
            plantel.getJugadores().add(jugador);
            con.updateJugadoresPlantel(conexion, jugador.getDni(), plantel.getId());
            con.updateJugadorIdPlantel(conexion, plantel.getId(), jugador.getDni());
            System.out.println("Se guardo un nuevo jugador");
            System.out.println("División: " + plantel.getDivision().getNombre());
            System.out.println("Cantidad de Jugadores Plantel: " + plantel.getJugadores().size());
        }
        try {
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * asigna un nuevo entrenador al plantel, director técnico o preparador físico.
     *
     * @param entrenador
     * @param club
     */
    public void asignarEntrenadorAPlantel(Entrenador entrenador, Club club) {
        System.out.println("Confirma Alta Entrenador? Si - No ");
        Scanner siNoScan = new Scanner(System.in);
        String opcionSiNo = siNoScan.next();

        if (opcionSiNo.equalsIgnoreCase("si")) {
            String division = entrenador.getDivision().getNombre();
            Plantel plantel = club.getPlanteles().get(division);
            if (entrenador.isPreparador()) {
                if (plantel.getPreparadorFisico() == null) {
                    plantel.setPreparadorFisico(entrenador);
                    System.out.println("Se asignó un preparador físico");
                } else {
                    System.out.println("Ya existe un preparador físico");
                }

            } else {
                if (plantel.getDirectorTecnico() == null) {
                    plantel.setDirectorTecnico(entrenador);
                    System.out.println("Se asignó un director técnico");
                } else {
                    System.out.println("Ya existe un director técnico");
                }

            }

            System.out.println("División: " + plantel.getDivision().getNombre());
            System.out.println("Cantidad de Jugadores Plantel: " + plantel.getJugadores().size());
            if (plantel.getDirectorTecnico() != null) {
                System.out.println("Director Técnico: " + plantel.getDirectorTecnico().getNombre() + " " + plantel.getDirectorTecnico().getApellido());
            }
            if (plantel.getPreparadorFisico() != null) {
                System.out.println("Preparador Físico: " + plantel.getPreparadorFisico().getNombre() + " " + plantel.getPreparadorFisico().getApellido());
            }
        }
        ConnectionDao con = new ConnectionDaoImpl();
        Connection conexion = con.getConnection();
        if (entrenador != null) {
            con.addEntrenador(conexion, entrenador);
        }
        try {
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * genera la lista de un equipo ordenado en base a la asistencia a entrenamiento.
     *
     * @param club
     * @return lista de jugadores ordenada.
     */
    public List<Jugador> generarEquipoPriorizado(Club club) {
        List<Jugador> jugadores;
        Scanner divisionScan = new Scanner(System.in);
        System.out.println("Ingrese División:");
        String division = divisionScan.next();
        Integer divisionSeleccionada = Division.getDivisiones().get(division);
        ConnectionDao con = new ConnectionDaoImpl();
        Connection conexion = con.getConnection();
        List<Jugador> jugadoresDB = con.getJugadoresAsistencia(conexion, divisionSeleccionada);
        try {
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Plantel plantel = club.getPlanteles().get(division);
        jugadores = getCuentaAsistenciaJugadores(jugadoresDB);
        Collections.sort(jugadores);
        Equipo equipo = new Equipo();
        equipo.setJugadores(jugadores);
        Partido partido = new Partido();
        LocalDate proximoPartido = LocalDate.now().with(next(SUNDAY));
        Date parsed = Date.from(proximoPartido.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        partido.setFechaPartido(parsed);
        equipo.setPartido(partido);
        plantel.setEquipoConvocado(equipo);
        System.out.println("Lista Priorizada, solo jugadores con asistencia: \n");
        for (Jugador jugador : jugadores) {
            System.out.println(jugador.getNombre() + " " + jugador.getApellido() + " - " + jugador.getPosicion().name() + " - " + "Asistió: " + jugador.getTotalPresente() + " días");
        }

        conexion = con.getConnection();
        con.updatePlantel(conexion, plantel);


        return jugadores;
    }

    /**
     * Calcula la asistencia y agrega el jugador a una nueva lista de jugadores priorizada
     *
     * @param jugadores
     * @return lista de jugadores priorizada.
     */
    private static List<Jugador> getCuentaAsistenciaJugadores(List<Jugador> jugadores) {
        List<Jugador> procesados;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int semanaActual = calendar.get(Calendar.WEEK_OF_YEAR);
        int anio = calendar.get(Calendar.YEAR);
        HashMap<String, Jugador> totales = new HashMap<>();
        for (Jugador jugador : jugadores) {
            for (var asistencia : jugador.getAsistencia().entrySet()) {
                String key = asistencia.getKey().split("-")[0];
                if (key.equalsIgnoreCase(semanaActual + "_" + anio)) {
                    Jugador registrarJugador = totales.get(jugador.getDni());
                    if (registrarJugador == null && asistencia.getValue().isPresente()) {
                        jugador.setTotalPresente();
                        totales.put(jugador.getDni(), jugador);
                    } else {
                        if (asistencia.getValue().isPresente()) {
                            registrarJugador.setTotalPresente();
                        }

                    }

                }
            }
        }
        procesados = new ArrayList<>(totales.values());

        return procesados;
    }

    /**
     * Genera un equipo ordenado a partir de la cantidad que solicite el usuario por consola.
     *
     * @param club
     * @return lista del tamaño seleccionado.
     */
    public List<Jugador> generarEquipoPriorizadoCantidad(Club club) {
        //TODO actualizar funcionalidad para usar la BD
        Scanner divisionScan = new Scanner(System.in);
        System.out.println("Ingrese División:");
        String division = divisionScan.next();
        Plantel plantel = club.getPlanteles().get(division);
        System.out.println("División:" + plantel.getDivision().getNombre());

        Scanner cantidadScan = new Scanner(System.in);
        System.out.println("Ingrese cantidad de jugadores requeridos: [8]-[11]");
        int cantidad = cantidadScan.nextInt();
        Integer divisionSeleccionada = Division.getDivisiones().get(division);
        ConnectionDao con = new ConnectionDaoImpl();
        Connection conexion = con.getConnection();
        List<Jugador> jugadores = con.getJugadores(conexion, divisionSeleccionada);
        try {
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<Jugador> listaCantidad = jugadores.subList(0, cantidad);
        System.out.println("Lista Priorizada: ");
        for (Jugador jugador : listaCantidad) {
            System.out.println("Nombre:" + jugador.getNombre() + " " + jugador.getApellido() + "-" + "Días:" + jugador.getTotalPresente());
        }
        return listaCantidad;
    }

    /**
     * imprime por pantalla los datos de los jugadores convocados.
     *
     * @param club
     * @return String con datos de los jugadores.
     */
    public String mostrarEquipoConvocado(Club club) {
        Scanner divisionScan = new Scanner(System.in);
        System.out.println("Ingrese División:");
        String division = divisionScan.next();
        Plantel plantel = club.getPlanteles().get(division);
        System.out.println("División:" + plantel.getDivision().getNombre());
        Equipo equipoConvocado = plantel.getEquipoConvocado();
        System.out.println("Convocados:");
        if (equipoConvocado != null && equipoConvocado.getJugadores().size() > 0) {
            for (Jugador jugador : equipoConvocado.getJugadores()) {
                System.out.println("DNI: " + jugador.getDni() + " - " + jugador.getNombre() + " " + jugador.getApellido() + " - " + jugador.getPosicion().name() + " - " + "Asistió: " + jugador.getTotalPresente() + " días");
            }
        } else {
            System.out.println("Aun no convoco a nigun equipo");
        }

        return division;
    }

    /**
     * permite la edicion de la lista de jugadores convocados.
     *
     * @param club
     * @throws JugadorDuplicadoException
     */
    public void editarEquipoConvocado(Club club) throws JugadorDuplicadoException {
        String division = mostrarEquipoConvocado(club);
        Scanner dniScan = new Scanner(System.in);
        System.out.println("Ingrese DNI de jugador a modificar:");
        String dni = dniScan.next();

        Plantel plantel = club.getPlanteles().get(division);
        ConnectionDao con = new ConnectionDaoImpl();
        Connection conexion = con.getConnection();

        List<Jugador> disponibles = con.getJugadores(conexion, Division.getDivisiones().get(division));
        try {
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Disponibles:");
        for (Jugador jugador : disponibles) {
            System.out.println("DNI: " + jugador.getDni() + " - " + jugador.getNombre() + " " + jugador.getApellido() + " - " + jugador.getPosicion().name());
        }
        Scanner nuevoScan = new Scanner(System.in);
        System.out.println("Ingrese DNI de jugador a convocar:");
        String nuevoJugadorDNI = nuevoScan.next();

        List<Jugador> convocados = plantel.getEquipoConvocado().getJugadores();

        Jugador eliminado = null;
        Jugador nuevo = null;

        for (int i = 0; i < convocados.size(); i++) {
            if (convocados.get(i).getDni().equalsIgnoreCase(dni)) {
                eliminado = convocados.get(i);
                convocados.remove(i);
                break;
            }
        }

        for (Jugador convocado : convocados) {
            if (convocado.getDni().equalsIgnoreCase(nuevoJugadorDNI)) {
                throw new JugadorDuplicadoException("El jugador ya está convocado");
            }
        }

        for (Jugador jugador : disponibles) {
            if (jugador.getDni().equalsIgnoreCase(nuevoJugadorDNI)) {
                convocados.add(jugador);
                nuevo = jugador;
                break;
            }
        }
        System.out.println("El jugador: " + eliminado.getNombre() + " " + eliminado.getApellido() + "- DNI: " + eliminado.getDni());
        System.out.println("Fue reemplazado por: ");
        System.out.println("El jugador: " + nuevo.getNombre() + " " + nuevo.getApellido() + "- DNI: " + nuevo.getDni());
    }

    /**
     * Elimina un jugador del plantel y de la lista de convocados.
     *
     * @param club
     */
    public void desafectarJugador(Club club) {
        Scanner divisionScan = new Scanner(System.in);
        System.out.println("Ingrese División:");
        String division = divisionScan.next();
        ConnectionDao con = new ConnectionDaoImpl();
        Connection conexion = con.getConnection();
        Plantel plantel = con.getPlantel(conexion, Division.getDivisiones().get(division));
        System.out.println("División:" + plantel.getDivision().getNombre());
        Scanner dniScan = new Scanner(System.in);
        List<Jugador> jugadores = plantel.getJugadores();

        for (Jugador j : jugadores) {
            System.out.println(j.mostrarDatosPersonales());
        }
        System.out.println("Ingrese DNI de jugador a desafectar:");
        String dni = dniScan.next();
        int idPlantel = plantel.getId();
        con.deleteJugador(conexion, dni);
        con.deleteJugadorIdPlantel(conexion, idPlantel, dni);
        for (Jugador j : jugadores) {
            if (j.getDni().equalsIgnoreCase(dni)) {
                System.out.println("Se desafecta al jugador DNI:" + j.getDni() + "- " + j.getNombre() + " " + j.getApellido());
                jugadores.remove(j);
                break;
            }
        }
        List<Jugador> convocados = plantel.getEquipoConvocado() != null ? plantel.getEquipoConvocado().getJugadores() : null;
        if (convocados != null && convocados.size() > 0) {
            for (Jugador j : convocados) {
                if (j.getDni().equalsIgnoreCase(dni)) {
                    convocados.remove(j);
                    break;
                }
            }
        }
        try {
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Division> getCategoriasDivision() {
        return categoriasDivision;
    }

}
