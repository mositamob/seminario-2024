package controlador;

import modelo.entidades.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EntrenadorControlador {

    /**
     * Crea un nuevo entrenador, director técnico o preparador físico
     * a partir de datos ingresados por consola
     *
     * @return un nuevo entrenador.
     */
    public Entrenador altaEntrenador() {

        Entrenador entrenador = null;
        Scanner nombreScan = new Scanner(System.in);
        Scanner apellidoScan = new Scanner(System.in);
        Scanner dniScan = new Scanner(System.in);
        Scanner fechaNacimientoScan = new Scanner(System.in);
        Scanner expScan = new Scanner(System.in);
        Scanner esDocenteScan = new Scanner(System.in);
        Scanner esPreparadorScan = new Scanner(System.in);
        Scanner divisionScan = new Scanner(System.in);
        System.out.println("Ingrese nombre:");
        String nombre = nombreScan.next();
        System.out.println("Ingrese apellido:");
        String apellido = apellidoScan.next();
        System.out.println("Ingrese DNI:");
        String dni = dniScan.next();

        System.out.println("Ingrease fecha nacimiento: dd-mm-yyyy");
        String fechaNacimiento = fechaNacimientoScan.next();
        Date fecha = null;
        try {
            fecha = new SimpleDateFormat("dd-MM-yyyy").parse(fechaNacimiento);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Ingrese años experiencia:");
        int añosExperiencia = expScan.nextInt();

        System.out.println("Es Docente: Si-No");
        String esDocenteOption = esDocenteScan.next();
        boolean esDocente = false;
        if (esDocenteOption.equalsIgnoreCase("si")) {
            esDocente = true;
        }

        System.out.println("Es preparador: Si-No");
        String esPreparadorOption = esPreparadorScan.next();
        boolean esPreparador = false;
        if (esPreparadorOption.equalsIgnoreCase("si")) {
            esPreparador = true;
        }
        System.out.println("Ingrese división:");
        String divisionValue = divisionScan.next();
        Division division = new Division(divisionValue);
        entrenador = new Entrenador(añosExperiencia, esDocente, esPreparador, dni, nombre, apellido, fecha, division);
        return entrenador;
    }
}
