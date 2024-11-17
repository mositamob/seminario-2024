package modelo.entidades;

public enum Posicion {
        ARQUERO("ARQUERO",1),
        DEFENSOR("DEFENSOR",2),
        INTERNO("INTERNO",3),
        DELANTERO("DELANTERO",4),
        EXTREMO("EXTREMO",5);

    private final String descripcion;
    private final int codigo;
    // Constructor del enum que recibe valores String e int
    Posicion(String descripcion, int codigo) {
        this.descripcion = descripcion;
        this.codigo = codigo;
    }

    // Métodos para obtener los valores
    public String getDescripcion() {
        return descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

}
