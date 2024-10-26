package controlador;

import modelo.entidades.*;
import modelo.excepciones.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.next;


public class PlantelControlador {
    public PlantelControlador() {
    }

    /**
     * Asigna un nuevo jugador a una divisón de los planteles del club.
     * @param jugador
     * @param club
     */
    public void asignarJugadorAPlantel(Jugador jugador, Club club) {
        System.out.println("Confirma Alta Jugador? Si - No ");
        Scanner siNoScan = new Scanner(System.in);
        String opcionSiNo = siNoScan.next();

        if (opcionSiNo.equalsIgnoreCase("si")) {
            String division = jugador.getCategoria().getDivision().getNombre();
            Plantel plantel = club.getPlanteles().get(division);
            plantel.getJugadores().add(jugador);
            System.out.println("Se guardo un nuevo jugador");
            System.out.println("División: " + plantel.getDivision().getNombre());
            System.out.println("Cantidad de Jugadores Plantel: " + plantel.getJugadores().size());
        }
    }

    /**
     * asigna un nuevo entrenador al plantel, director técnico o preparador físico.
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
    }

    /**
     * genera la lista de un equipo ordenado en base a la asistencia a entrenamiento.
     * @param club
     * @return lista de jugadores ordenada.
     */
    public List<Jugador> generarEquipoPriorizado(Club club) {
        List<Jugador> jugadores;
        Scanner divisionScan = new Scanner(System.in);
        System.out.println("Ingrese División:");
        String division = divisionScan.next();
        Plantel plantel = club.getPlanteles().get(division);
        System.out.println("División:" + plantel.getDivision().getNombre());
        jugadores = getJugadoresPriorizados(plantel);
        Collections.sort(jugadores);
        Equipo equipo = new Equipo();
        equipo.setJugadores(jugadores);
        Partido partido = new Partido();
        LocalDate proximoPartido = LocalDate.now().with(next(SUNDAY));
        Date parsed = Date.from(proximoPartido.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        partido.setFechaPartido(parsed);
        equipo.setPartido(partido);
        plantel.setEquipoConvocado(equipo);
        System.out.println("Lista Priorizada: ");
        for (Jugador jugador : jugadores) {
            System.out.println("Nombre:" + jugador.getNombre() + " " + jugador.getApellido() + "-" + "Días:" + jugador.getTotalPresente());
        }
        return jugadores;
    }

    /**
     * Calcula la asistencia y agrega el jugador a una nueva lista de jugadores priorizada
     * @param plantel
     * @return lista de jugadores priorizada.
     */
    private static List<Jugador> getJugadoresPriorizados(Plantel plantel) {
        List<Jugador> jugadores = plantel.getJugadores();
        List<Jugador> priorizados = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int semanaActual = calendar.get(Calendar.WEEK_OF_YEAR);
        for (Jugador jugador : jugadores) {
            jugador.calcularClasesPresente(String.valueOf(semanaActual));
            priorizados.add(jugador);
        }
        return priorizados;
    }

    /**
     * Genera un equipo ordenado a partir de la cantidad que solicite el usuario por consola.
     * @param club
     * @return lista del tamaño seleccionado.
     */
    public List<Jugador> generarEquipoPriorizadoCantidad(Club club) {
        Scanner divisionScan = new Scanner(System.in);
        System.out.println("Ingrese División:");
        String division = divisionScan.next();
        Plantel plantel = club.getPlanteles().get(division);
        System.out.println("División:" + plantel.getDivision().getNombre());

        Scanner cantidadScan = new Scanner(System.in);
        System.out.println("Ingrese cantidad de jugadores requeridos: [8]-[11]");
        int cantidad = cantidadScan.nextInt();

        List<Jugador> jugadores = getJugadoresPriorizados(plantel);
        List<Jugador> listaCantidad = jugadores.subList(0, cantidad);
        System.out.println("Lista Priorizada: ");
        for (Jugador jugador : listaCantidad) {
            System.out.println("Nombre:" + jugador.getNombre() + " " + jugador.getApellido() + "-" + "Días:" + jugador.getTotalPresente());
        }
        return listaCantidad;
    }

    /**
     * imprime por pantalla los datos de los jugadores convocados.
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
        for (Jugador jugador : equipoConvocado.getJugadores()) {
            System.out.println("Nombre:" + jugador.getNombre() + " " + jugador.getApellido() + "-" + "Posición:" + jugador.getPosicion().getNombre() + "- DNI: " + jugador.getDni());
        }
        return division;
    }

    /**
     * permite la edicion de la lista de jugadores convocados.
     * @param club
     * @throws JugadorDuplicadoException
     */
    public void editarEquipoConvocado(Club club) throws JugadorDuplicadoException {
        String division = mostrarEquipoConvocado(club);
        Scanner dniScan = new Scanner(System.in);
        System.out.println("Ingrese DNI de jugador a editar:");
        String dni = dniScan.next();
        Plantel plantel = club.getPlanteles().get(division);
        List<Jugador> convocados = plantel.getEquipoConvocado().getJugadores();
        List<Jugador> disponibles = plantel.getJugadores();
        Jugador eliminado = null;
        Jugador nuevo = null;

        for (int i = 0; i < convocados.size(); i++) {
            if (convocados.get(i).getDni().equalsIgnoreCase(dni)) {
                eliminado = convocados.get(i);
                convocados.remove(i);
                break;
            }
        }
        Scanner nuevoScan = new Scanner(System.in);
        System.out.println("Ingrese DNI de jugador a convocar:");
        String nuevoJugadorDNI = nuevoScan.next();

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
     * @param club
     */
    public void desafectarJugador(Club club) {
        Scanner divisionScan = new Scanner(System.in);
        System.out.println("Ingrese División:");
        String division = divisionScan.next();
        Plantel plantel = club.getPlanteles().get(division);
        System.out.println("División:" + plantel.getDivision().getNombre());
        Scanner dniScan = new Scanner(System.in);
        System.out.println("Ingrese DNI de jugador a desafectar:");
        String dni = dniScan.next();

        List<Jugador> jugadores = plantel.getJugadores();

        for (Jugador j : jugadores) {
            if (j.getDni().equalsIgnoreCase(dni)) {
                System.out.println("Se desafecta al jugado DNI:" + j.getDni() + "- " + j.getNombre() + " " + j.getApellido());
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

    }
}
