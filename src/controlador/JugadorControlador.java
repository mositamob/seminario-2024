package controlador;

import dao.ConnectionDao;
import dao.ConnectionDaoImpl;
import modelo.entidades.*;
import modelo.excepciones.InvalidAñoCategoriaException;
import modelo.excepciones.InvalidFormatoFechaException;
import modelo.excepciones.InvalidPosicionException;
import modelo.excepciones.NoHayJugadoresException;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JugadorControlador extends PlantelControlador {
    /**
     * (3[01]|[12][0-9]|0[1-9]): Este grupo valida el día.
     * 3[01]: Acepta los días 30 o 31 (días válidos para ciertos meses).
     * [12][0-9]: Acepta los días 10 al 29.
     * 0[1-9]: Acepta los días 01 al 09.
     * <p>
     * (1[0-2]|0[1-9]): Este grupo valida el mes.
     * 1[0-2]: Acepta los meses 10, 11 o 12.
     * 0[1-9]: Acepta los meses 01 al 09.
     * [0-9]{4}: Esta parte valida el año, permitiendo cualquier año compuesto por cuatro dígitos.
     */
    private static String REGEX = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";


    private Map<String, String> posiciones = new HashMap<>() {
        {
            put("1", "ARQUERO");
            put("2", "DEFENSOR");
            put("3", "INTERNO");
            put("4", "DELANTERO");
            put("5", "EXTREMO");
        }
    };

    /**
     * Crea los objetos para leer los  datos ingresados por consola.
     *
     * @return nuevo jugador.
     * @throws ParseException
     * @throws InvalidPosicionException
     * @throws InvalidAñoCategoriaException
     * @throws InvalidFormatoFechaException
     */
    public Jugador altaJugador() throws ParseException, InvalidPosicionException, InvalidAñoCategoriaException, InvalidFormatoFechaException {
        Scanner nombreScan = new Scanner(System.in);
        Scanner apellidoScan = new Scanner(System.in);
        Scanner aptoFisicoValue = new Scanner(System.in);
        Scanner dniScan = new Scanner(System.in);
        Scanner fechaNacimientoScan = new Scanner(System.in);
        Scanner categoriaScan = new Scanner(System.in);
        Scanner posicionScan = new Scanner(System.in);
        return altaJugador(nombreScan, apellidoScan, aptoFisicoValue, dniScan, fechaNacimientoScan, categoriaScan, posicionScan);
    }

    /**
     * Solicita ingreso de datos y crea un nuevo jugador.
     *
     * @param nombreScan
     * @param apellidoScan
     * @param aptoFisicoValue
     * @param dniScan
     * @param fechaNacimientoScan
     * @param categoriaScan
     * @param posicionScan
     * @return un nuevo jugador.
     * @throws ParseException
     * @throws InvalidAñoCategoriaException
     * @throws InvalidPosicionException
     * @throws InvalidFormatoFechaException
     */
    public Jugador altaJugador(Scanner nombreScan, Scanner apellidoScan, Scanner aptoFisicoValue, Scanner dniScan, Scanner fechaNacimientoScan, Scanner categoriaScan, Scanner posicionScan) throws ParseException, InvalidAñoCategoriaException, InvalidPosicionException, InvalidFormatoFechaException {
        Jugador jugador;
        System.out.println("Alta nuevo jugador");
        System.out.println("Ingrese nombre:");
        String nombre = nombreScan.next();
        System.out.println("Ingrese apellido:");
        String apellido = apellidoScan.next();
        System.out.println("Ingrese DNI:");
        String dni = dniScan.next();

        System.out.println("Ingrease fecha nacimiento: dd-mm-yyyy");
        String fechaNacimiento = fechaNacimientoScan.next();
        validateFormatoFecha(fechaNacimiento);
        System.out.println("Ingrese Categoría:");
        String categoriaValue = categoriaScan.next();
        Division division = categoriasDivision.get(categoriaValue);
        if (division == null) {
            throw new InvalidAñoCategoriaException("Ingrese una categoría válida");
        }
        System.out.println("Ingrese opción posición:");
        System.out.println("1- ARQUERO");
        System.out.println("2- DEFENSOR");
        System.out.println("3- INTERNO");
        System.out.println("4- DELANTERO");
        System.out.println("5- EXTREMO");

        String posicionOpcion = posicionScan.next();
        Posicion posicion = Posicion.valueOf(posiciones.get(posicionOpcion));
        if (posicion == null) {
            throw new InvalidPosicionException("Ingrese una opción válida");
        }
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaValue);
        categoria.setDivision(division);
        String[] fechas = fechaNacimiento.split("-");
        String añoNacimiento = fechas[2];
        validateCategoriaFecha(añoNacimiento, categoria.getNombre());
        Date fecha = new SimpleDateFormat("dd-MM-yyyy").parse(fechaNacimiento);
        System.out.println("presenta apto fisico:SI-NO");
        String aptoFisicoScan = aptoFisicoValue.next();
        boolean aptoFisico = false;
        if (aptoFisicoScan.equalsIgnoreCase("si")) {
            aptoFisico = true;
        } else if (aptoFisicoScan.equalsIgnoreCase("no")) {
            aptoFisico = false;
        }
        jugador = new Jugador(dni, nombre, apellido, fecha, categoria, posicion, aptoFisico);

        System.out.println("Nuevo Jugador:" + jugador.mostrarDatosPersonales());

        System.out.println("-------------------------------------------");
        ConnectionDao con = new ConnectionDaoImpl();
        Connection conexion = con.getConnection();
        con.addJugador(conexion, jugador);
        try {
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return jugador;
    }

    /**
     * valida que la fecha tenga el formato dd-mm-yyyy.
     *
     * @param fecha dato string ingresado por consola.
     * @throws InvalidFormatoFechaException
     */
    private void validateFormatoFecha(String fecha) throws InvalidFormatoFechaException {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(fecha);
        if (!matcher.matches()) {
            throw new InvalidFormatoFechaException("El formato de la fecha es invalido");
        }

    }

    /**
     * validación de la categoria corresponda con la fecha de nacimiento.
     *
     * @param añoNacimiento
     * @param categoria
     * @throws InvalidAñoCategoriaException
     */
    private void validateCategoriaFecha(String añoNacimiento, String categoria) throws InvalidAñoCategoriaException {
        //TODO validar con la fecha de nacimiento categoria+1 si mes nacimiento es entre julio y diciembre
        if (!añoNacimiento.equalsIgnoreCase(categoria)) {
            throw new InvalidAñoCategoriaException("La categoria no coincide con el año de nacimiento");
        }
    }

    /**
     * lista los jugadores de una división ingresada por consola.
     *
     * @param club
     */
    public void listarJugadores(Club club) {
        Scanner divisionScan = new Scanner(System.in);
        System.out.println("3. Listar judagores Equipo");
        System.out.println("Ingrese Division del Equipo:");
        String divisionSeleccionada = divisionScan.next();
        System.out.println("División Seleccionada: " + divisionSeleccionada);
        Plantel plantel = club.getPlanteles().get(divisionSeleccionada);
        ConnectionDao con = new ConnectionDaoImpl();
        Connection conexion = con.getConnection();

        List<Jugador> listaJugadores = con.getJugadores(conexion, Division.getDivisiones().get(divisionSeleccionada));
        try {
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            club.validateExistenJugadores(listaJugadores);

            for (Jugador jugador : listaJugadores) {
                System.out.println(jugador.mostrarDatosPersonales());
            }
            if (plantel.getDirectorTecnico() != null) {
                plantel.getDirectorTecnico().mostrarDatosPersonales();
            }
            if (plantel.getPreparadorFisico() != null) {
                plantel.getPreparadorFisico().mostrarDatosPersonales();
            }

            System.out.println("-------------------------------------------");
            System.out.println("-------------------------------------------");
        } catch (NoHayJugadoresException e) {
            throw new RuntimeException(e);
        }
    }
}
