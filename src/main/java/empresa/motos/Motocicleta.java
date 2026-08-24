package empresa.motos;



public class Motocicleta {
    
    /**
     * Se generaran los atributos (caracteristicas) de todas las motocicletas
     * 
     */
    
    private int id;
    private String placa;
    private String marca;
    private int modelo;
    private double precio;
    private boolean disponible;
    private String estado;
    
    /**
     * Cree el metodo constructor para darle sus valores iniciales
     */
    public Motocicleta(int id, String placa, String marca, int modelo, double precio, boolean disponible, String estado) 
    {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.disponible = disponible;
        this.estado = estado;
    }
    /**
     * se genera los metodos gets and setter para poder adquirir o establecer el valor de los atributos
     * 
     */

    public void setId(int id) {
        this.id = id;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
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

    public String getMarca() {
        return marca;
    }

    public int getModelo() {
        return modelo;
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
