package empresa.motos;

public class Motocicleta {

    /**
     * Se generaran los atributos (caracteristicas) de todas las motocicletas
     *
     */
    private int id;
    private String placa;
    private String marca;
    private double precio;
    private boolean disponible;
    private char categoria;
    private String estado;
    private int codigoConcesionario;

    public Motocicleta() {

    }

    /**
     * Cree el metodo constructor para darle sus valores iniciales
     */
    public Motocicleta(int id, String placa, String marca, double precio, boolean disponible, char categoria, String estado, int codigoConcesionario) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.precio = precio;
        this.disponible = disponible;
        this.categoria = categoria;
        this.estado = estado;
        this.codigoConcesionario = codigoConcesionario;
        
    }

    public int getCodigoConcesionario() {
        return codigoConcesionario;
    }

    public void setCodigoConcesionario(int codigoConcesionario) {
        this.codigoConcesionario = codigoConcesionario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }


    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public char getCategoria() {
        return categoria;
    }

    public void setCategoria(char categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public String getEstado() {
        return estado;
    }

}
