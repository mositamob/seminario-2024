package vista;

import controlador.*;
import modelo.entidades.*;
import modelo.excepciones.*;

import java.text.ParseException;
import java.util.Scanner;

public class Menu {
    private static Club club = new Club("DIEF", "28/02/2016");
    private JugadorControlador jugadorControlador = new JugadorControlador();
    private PlantelControlador plantelControlador = new PlantelControlador();
    private EntrenadorControlador entrenadorControlador = new EntrenadorControlador();
    private AsistenciaControlador asistenciaControlador = new AsistenciaControlador();

    public Menu() {
    }

    /**
     * Menu de opciones.
     * @throws ParseException
     */
    public void mostrarOpciones() throws ParseException {
        System.out.println("Seleccione opción:");
        Scanner opcionesScan = new Scanner(System.in);

        boolean salir = false;
        int opcion; // opcion del usuario

        do {
            System.out.println("\n");
            System.out.println("---------- MENU DE OPCIONES ----------");

            System.out.println("1. Alta Jugador");
            System.out.println("2. Alta Entrenador");
            System.out.println("3. Listar Jugadores");
            System.out.println("4. Registrar Asistencia");
            System.out.println("5. Generar Equipo Priorizado");
            System.out.println("6. Equipo Priorizado por Cantidad");
            System.out.println("7. Mostrar Equipo Convocado");
            System.out.println("8. Editar Equipo Convocado");
            System.out.println("9. Desafectar Jugador");

            System.out.println("0. Salir ");
            System.out.println("Elije una de las opciones:");
            opcion = opcionesScan.nextInt();

            switch (opcion) {
                case 1:
                    Jugador jugador = null;
                    try {
                        jugador = jugadorControlador.altaJugador();
                    } catch (InvalidPosicionException e) {
                        System.out.println("Ingresó una opción inválida :\n" + e.getMessage());
                        mostrarOpciones();

                    } catch (InvalidAñoCategoriaException e) {
                        System.out.println("Ingresó una opción inválida :\n" + e.getMessage());
                        mostrarOpciones();
                    } catch (InvalidFormatoFechaException e) {
                        System.out.println("Ingresó una opción inválida :\n" + e.getMessage());
                    }
                    if (jugador != null) {
                        plantelControlador.asignarJugadorAPlantel(jugador, club);
                    }

                    break;
                case 2:
                    Entrenador entrenador = entrenadorControlador.altaEntrenador();
                    if (entrenador != null) {
                        plantelControlador.asignarEntrenadorAPlantel(entrenador, club);
                    }
                    break;
                case 3:
                    jugadorControlador.listarJugadores(club);
                    break;
                case 4:
                    asistenciaControlador.registrarAsistencia(club);
                    break;
                case 5:
                   plantelControlador.generarEquipoPriorizado(club);
                    break;
                case 6:
                    plantelControlador.generarEquipoPriorizadoCantidad(club);
                    break;
                case 7:
                    plantelControlador.mostrarEquipoConvocado(club);
                    break;
                  case 8:
                      try {
                          plantelControlador.editarEquipoConvocado(club);
                      } catch (JugadorDuplicadoException e) {
                          System.out.println("Ingresó una opción inválida :\n" + e.getMessage());
                          mostrarOpciones();
                      }
                      break;
                case 9:
                    plantelControlador.desafectarJugador(club);
                    break;
                case 0:
                    System.out.println("0. Salir ");
                    salir = true;
                    break;
                default:
                    System.out.println("No es una opción válida ");
                    salir = true;
                    break;
            }
        } while (!salir);
    }

}
