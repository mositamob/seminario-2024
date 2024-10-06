package entidades;


import java.util.List;
import java.util.Map;

public class Club {
    private String nombre;
    private String fechaInauguración;
    private List<Plantel> planteles;

    public List<Plantel> getPlanteles() {
        return planteles;
    }

    public void setPlanteles(List<Plantel> planteles) {
        this.planteles = planteles;
    }

    public Club() {
    }

    public Club(String nombre, String fechaInauguración) {
        this.nombre = nombre;
        this.fechaInauguración = fechaInauguración;
    }

    public void guardarEquipo() {

        //equipos.put(categoria, equipo);
    }


    public void validateEquipos(Map<String, Equipo> equipos ) {
//        if (equipos.size() == 0) {
//            throw new NoHayEquiposException("No existen equipos cargados");
//        }
    }

//    //public List<String> listarJugadoresDeEntrenador(String nombreEntrenador) {
//        return new ArrayList<>();
//    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaInauguración() {
        return fechaInauguración;
    }

    public void setFechaInauguración(String fechaInauguración) {
        this.fechaInauguración = fechaInauguración;
    }

}
