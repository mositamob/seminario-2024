package entidades;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Equipo {

    private List<Jugador> jugadores;
    private Partido partido;


    public Equipo() {
    }


    public List<Jugador> getJugadores() {
//        if (jugadores != null && jugadores.size() > 0) {
//            return jugadores;
//        }
        return new ArrayList<>();
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }


    public void mostrarJugadores(Equipo equipo) {
        List<Jugador> jugadoresActuales = equipo.getJugadores();
        if (jugadoresActuales.size() > 0) {
            int i = 1;
            for (Jugador jug : jugadoresActuales) {
                System.out.println("Jugador Nº: " + i + " " + jug.getNombre() + " " + jug.getApellido());
                i++;
            }
        }
    }

    public void crearEquipo(List<Jugador> jugadores, String añoCategoria, Club club) {
//        System.out.println("Guardando nuevo equipo categoria: " + añoCategoria);
//        Categoria categoria = new Categoria();
//        categoria.setAño(añoCategoria);
//        Entrenador entrenador = new Entrenador();
//        entrenador = entrenador.definirEntrenador(añoCategoria, club);
//        Equipo equipo = new Equipo(categoria, jugadores, entrenador);
//        club.guardarEquipo(equipo, añoCategoria);
//        System.out.println("Equipo categoria: " + añoCategoria + " " + "guardado con éxito");
//        System.out.println("---------------------------------------------------------------");
    }

    public void cargarNuevoJugador() {
//        Jugador jugador = new Jugador();
//        try {
//            jugador = jugador.altaNuevoJugador(nombreScan, apellidoScan, aptoFisicoValue, dniScan, fechaNacimientoScan, categoriaEquipo);
//            System.out.println("Confirma Alta Jugador? Si - No ");
//            Scanner siNoScan = new Scanner(System.in);
//            String opcionSiNo = siNoScan.next();
//            List<Jugador> jugadores = club.getEquipos().get(categoriaEquipo).getJugadores();
//            if (opcionSiNo.equalsIgnoreCase("si")) {
//                jugadores.add(jugador);
//                club.getEquipos().get(categoriaEquipo).setJugadores(jugadores);
//                System.out.println("se guardo un nuevo jugador");
//            }
//            System.out.println("Desea cargar otro jugador? 1: cargar otro jugador - 7: Guardar");
//            int opcion = opcionesScan.nextInt();
//            if (opcion == 1) {
//                cargarNuevoJugador(nombreScan, apellidoScan, aptoFisicoValue, dniScan, fechaNacimientoScan, opcionesScan, categoriaEquipo, club);
//            } else if (opcion == 7) {
//                Equipo equipo = new Equipo();
//                equipo.crearEquipo(jugadores, categoriaEquipo, club);
//            }
//        } catch (InvalidAñoCategoriaException e) {
//            e.printStackTrace();
//        }
    }

    public HashMap<String, List<Integer>> contarPartidosJugadosPorJugador() {
//        HashMap<String, List<Integer>> cuenta = new HashMap<>();
//        for (Partido p : partidosJugados) {
//            for (Jugador j : p.getFormacionLocal().getJugadores()) {
//                if (cuenta.get(j.getDni()) != null) {
//                    cuenta.get(j.getDni()).add(1);
//                } else {
//                    List<Integer> contadorPartidos = new ArrayList<>();
//                    contadorPartidos.add(1);
//                    cuenta.put(j.getDni(), contadorPartidos);
//                }
//            }
//        }
        return null;
    }

//    public void validateJugadoresEnEquipo(List<Jugador> jugadores) throws NoHayEquiposException {
//        if (jugadores.size() == 0) {
//            throw new NoHayEquiposException("La categoria no coincide con la fecha");
//        }
//    }

    public List<Jugador> listarJugadoresSinPartidos() {
        List<Jugador> sinJugar = new ArrayList<>();
        HashMap<String, List<Integer>> cuenta = contarPartidosJugadosPorJugador();
        for (Jugador ju : jugadores) {
            String key = ju.getDni();
            if (cuenta.get(key) == null) {
                sinJugar.add(ju);
            }
        }
        return sinJugar;
    }

}
