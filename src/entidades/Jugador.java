package entidades;


public class Jugador extends Persona {
    private Categoria categoria;
    private Posicion posicion;
    private boolean aptoFisico;
    private Asistencia asistencia;
   // private static String REGEX = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";

    public Jugador() {
    }

    public Asistencia getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Asistencia asistencia) {
        this.asistencia = asistencia;
    }



    public Categoria getCategoria() {
        return categoria;
    }

    public Jugador altaNuevoJugador() {
//        Jugador jugador = new Jugador();
//        System.out.println("Alta nuevo jugador categoria: " + categoriaEquipo);
//        System.out.println("Ingrese nombre:");
//        String nombre = nombreScan.next();
//        System.out.println("Ingrese apellido:");
//        String apellido = apellidoScan.next();
//        System.out.println("Ingrese DNI:");
//        String dni = dniScan.next();
//
//        System.out.println("Ingrease fecha nacimiento: dd-mm-yyyy");
//        String fechaNacimiento = fechaNacimientoScan.next();
//
//        try {
//            validateFormatoFecha(fechaNacimiento);
//            Categoria categoria = new Categoria();
//            String[] fechas = fechaNacimiento.split("-");
//            String añoNacimiento = fechas[2];
//            categoria.setAño(añoNacimiento);
//            validateCategoriaFecha(añoNacimiento, categoriaEquipo);
//            Date fecha = new SimpleDateFormat("dd-MM-yyyy").parse(fechaNacimiento);
//            System.out.println("presenta apto fisico:SI-NO");
//            String aptoFisicoScan = aptoFisicoValue.next();
//            boolean aptoFisico = false;
//            if (aptoFisicoScan.equalsIgnoreCase("si")) {
//                aptoFisico = true;
//            } else if (aptoFisicoScan.equalsIgnoreCase("no")) {
//                aptoFisico = false;
//            }
//            jugador = new Jugador(categoria, aptoFisico, dni, nombre, apellido, fecha);
//            System.out.println("Nuevo Jugador:" + jugador.getApellido() + " " + jugador.getNombre() + " " + jugador.getCategoria().getAño());
//        } catch (InvalidFormatoFechaException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("-------------------------------------------");
//        System.out.println("-------------------------------------------");
        return null;
    }

//    private void validateFormatoFecha(String fecha) throws InvalidFormatoFechaException {
//        Pattern pattern = Pattern.compile(REGEX);
//        Matcher matcher = pattern.matcher(fecha);
//        if (!matcher.matches()) {
//            throw new InvalidFormatoFechaException("El formato de la fecha es invalido");
//        }
//
//    }

//    private void validateCategoriaFecha(String fecha, String categoria) throws InvalidAñoCategoriaException {
//        if (!fecha.equalsIgnoreCase(categoria)) {
//            throw new InvalidAñoCategoriaException("La categoria no coincide con la fecha");
//        }
//    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public boolean isAptoFisico() {
        return aptoFisico;
    }

    public void setAptoFisico(boolean aptoFisico) {
        this.aptoFisico = aptoFisico;
    }

//    @Override
//    public void mostrarDatosPersonales(Persona persona) {
//        Jugador jugador = (Jugador) persona;
//        System.out.println("Categoria: " + jugador.getCategoria().getAño() + " - DNI : " + jugador.getDni() + "- Nombre: " + jugador.getNombre() + " " + jugador.getApellido() + " - Apto Fisico: " + (jugador.isAptoFisico() ? "si" : "no"));
//    }
}
