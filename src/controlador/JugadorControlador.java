package controlador;

import modelo.entidades.*;
import modelo.excepciones.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JugadorControlador {
    /**
     * (3[01]|[12][0-9]|0[1-9]): Este grupo valida el día.
     * 3[01]: Acepta los días 30 o 31 (días válidos para ciertos meses).
     * [12][0-9]: Acepta los días 10 al 29.
     * 0[1-9]: Acepta los días 01 al 09.
     *
     * (1[0-2]|0[1-9]): Este grupo valida el mes.
     * 1[0-2]: Acepta los meses 10, 11 o 12.
     * 0[1-9]: Acepta los meses 01 al 09.
     * [0-9]{4}: Esta parte valida el año, permitiendo cualquier año compuesto por cuatro dígitos.
     */
    private static String REGEX = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";

    private Map<String, Division> categoriasDivision = new HashMap<>() {
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
    private Map<String, String> posiciones = new HashMap<>() {
        {
            put("1", "arquero");
            put("2", "defensor");
            put("3", "mediocampo");
            put("4", "delantero");
        }
    };


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
        System.out.println("1- Arquero");
        System.out.println("2- Defensor");
        System.out.println("3- Mediocampo");
        System.out.println("4- Delantero");

        String posicionOpcion = posicionScan.next();
        String posicionValue = posiciones.get(posicionOpcion);
        if (posicionValue == null) {
            throw new InvalidPosicionException("Ingrese una opción válida");
        }
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaValue);
        categoria.setDivision(division);
        Posicion posicion = new Posicion();
        posicion.setNombre(posicionValue);
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
        return jugador;
    }

    private void validateFormatoFecha(String fecha) throws InvalidFormatoFechaException {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(fecha);
        if (!matcher.matches()) {
            throw new InvalidFormatoFechaException("El formato de la fecha es invalido");
        }

    }

    private void validateCategoriaFecha(String añoNacimiento, String categoria) throws InvalidAñoCategoriaException {
        //TODO validar con la fecha de nacimiento categoria+1 si mes nacimiento es entre julio y diciembre
        if (!añoNacimiento.equalsIgnoreCase(categoria)) {
            throw new InvalidAñoCategoriaException("La categoria no coincide con el año de nacimiento");
        }
    }

    public void listarJugadores(Club club) {
        Scanner divisionScan = new Scanner(System.in);
        System.out.println("3. Listar judagores Equipo");
        System.out.println("Ingrese Division del Equipo:");
        String divisionSeleccionada = divisionScan.next();
        System.out.println("División Seleccionada: " + divisionSeleccionada);
        Plantel plantel = club.getPlanteles().get(divisionSeleccionada);
        try {
            club.validateExistenJugadores(plantel);
            List<Jugador> listaJugadores = plantel.getJugadores();
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
