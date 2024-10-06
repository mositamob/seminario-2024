package entidades;




public class Categoria {
    private String año;
    private Division division;
    private String subCategoria;

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(String subCategoria) {
        this.subCategoria = subCategoria;
    }

    public void validarCategoriaHabilitada(String año) {
//        ArrayList<String> habilitadas = new ArrayList<>();
//        habilitadas.add("2010");
//        habilitadas.add("2011");
//        habilitadas.add("2012");
//        habilitadas.add("2013");
//
//        if (!habilitadas.contains(año)) {
//            throw new InvalidCategoriaException("Categoria Seleccionada Invalida: " + año);
//        }

    }

}
