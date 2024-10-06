package entidades;


import java.util.Date;

public class Entrenador extends Persona{
    private int añosExperiencia;
    private boolean isDocente;
    private boolean isPreparador;

    public Entrenador() {
        super();
    }

    public Entrenador(String dni, String nombre, String apellido, Date fechaNacimiento) {
        super(dni, nombre, apellido, fechaNacimiento);
    }

    public Entrenador(int añosExperiencia, boolean isDocente, String dni, String nombre, String apellido, Date fechaNacimiento) {
        super(dni, nombre, apellido, fechaNacimiento);
        this.añosExperiencia = añosExperiencia;
        this.isDocente = isDocente;
    }

    public int getAñosExperiencia() {
        return añosExperiencia;
    }

    public void setAñosExperiencia(int añosExperiencia) {
        this.añosExperiencia = añosExperiencia;
    }

    public boolean isDocente() {
        return isDocente;
    }

    public void setDocente(boolean docente) {
        isDocente = docente;
    }

    public boolean isPreparador() {
        return isPreparador;
    }

    public void setPreparador(boolean preparador) {
        isPreparador = preparador;
    }

//    public Entrenador definirEntrenador(String añoCategoria, Club club) {
//        Entrenador entrenador = null;
//        Scanner entrenadorScan = new Scanner(System.in);
//        List<Entrenador> entrenadores = club.getEntrenadores();
//        if (entrenadores.size() == 0) {
//            System.out.println("Alta nuevo entrenador");
//            altaEntrenador(club);
//            entrenador = definirEntrenador(añoCategoria, club);
//        } else if (entrenadores.size() == 1) {
//            entrenador = entrenadores.get(0);
//        } else if (entrenadores.size() > 1) {
//            int i = 1;
//            for (Entrenador en : entrenadores) {
//                System.out.println("Entrenador " + i + ": " + en.getNombre() + " " + en.getApellido());
//                i++;
//            }
//            if (club.getEquipos().get(añoCategoria).getEntrenador() == null) {
//                System.out.println("Seleccione número opción de entrenador: ");
//                int opcion = entrenadorScan.nextInt();
//                entrenador = entrenadores.get(opcion - 1);
//            } else {
//                entrenador = club.getEquipos().get(añoCategoria).getEntrenador();
//            }
//        }
//        return entrenador;
//    }

//    public Entrenador definirEntrenador(Club club) {
//        Entrenador entrenador = null;
//        Scanner entrenadorScan = new Scanner(System.in);
//        List<Entrenador> entrenadores = club.getEntrenadores();
//        if (entrenadores.size() == 0) {
//            System.out.println("No existen Entrenadores para mostrar. Debe dar de alta entrenadores");
//            //lanzar exception
//        }
//        if (entrenadores.size() > 0) {
//            int i = 1;
//            for (Entrenador en : entrenadores) {
//                System.out.println("Entrenador " + i + ": " + en.getNombre() + " " + en.getApellido());
//                i++;
//            }
//            System.out.println("Seleccione número opción de entrenador: ");
//            int opcion = entrenadorScan.nextInt();
//            entrenador = entrenadores.get(opcion - 1);
//        }
//        return entrenador;
//    }

    public void altaEntrenador() {
//        Scanner nombreScan = new Scanner(System.in);
//        Scanner apellidoScan = new Scanner(System.in);
//        Scanner dniScan = new Scanner(System.in);
//        Scanner fechaNacimientoScan = new Scanner(System.in);
//        Scanner expScan = new Scanner(System.in);
//        Scanner esDocenteScan = new Scanner(System.in);
//        System.out.println("Ingrese nombre:");
//        String nombre = nombreScan.next();
//        System.out.println("Ingrese apellido:");
//        String apellido = apellidoScan.next();
//        System.out.println("Ingrese DNI:");
//        String dni = dniScan.next();
//
//        System.out.println("Ingrease fecha nacimiento: dd-mm-yyyy");
//        String fechaNacimiento = fechaNacimientoScan.next();
//        Date fecha = null;
//        try {
//            fecha = new SimpleDateFormat("dd-MM-yyyy").parse(fechaNacimiento);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Ingrese años experiencia:");
//        int añosExperiencia = expScan.nextInt();
//
//        System.out.println("Es Docente: Si-No");
//        String esDocenteOption = esDocenteScan.next();
//        boolean esDocente = false;
//        if (esDocenteOption.equalsIgnoreCase("si")) {
//            esDocente = true;
//        }
//        System.out.println("Confirma Alta Entrenador? Si - No ");
//        Scanner siNoScan = new Scanner(System.in);
//        String opcionSiNo = siNoScan.next();
//        if (opcionSiNo.equalsIgnoreCase("si")) {
//            Entrenador entrenador = new Entrenador(añosExperiencia, esDocente, dni, nombre, apellido, fecha);
//            List<Entrenador> entrenadores = club.getEntrenadores();
//            entrenadores.add(entrenador);
//            club.setEntrenadores(entrenadores);
//            System.out.println("Alta entrenador realizada con éxito: " + entrenador.getNombre() + " " + entrenador.getApellido());
//        }
    }
//
//    @Override
//    public void mostrarDatosPersonales(Persona persona) {
//        Entrenador entrenador = (Entrenador)persona;
//        System.out.println( "DNI : "+ entrenador.getDni()
//                + "- Nombre: " +entrenador.getNombre() + " " + entrenador.getApellido()
//                + " - Años Experiencia: "+ entrenador.getAñosExperiencia()
//                +"- Es Docente: "+ (entrenador.isDocente ? "si":"no"));
//
//    }
}
