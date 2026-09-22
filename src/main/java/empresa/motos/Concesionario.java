/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empresa.motos;

/**
 *
 * @author bloqu
 */
public class Concesionario {
    
    private int codigo;
    private long Telefono;
    private String direccion;
    private char categoria;
    private double presupuesto;

    public Concesionario(int codigo, long Telefono, String direccion, char categoria, double presupuesto) {
        this.codigo = codigo;
        this.Telefono = Telefono;
        this.direccion = direccion;
        this.categoria = categoria;
        this.presupuesto = presupuesto;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }
    
    
    public int getCodigo() {
        return codigo;
    }

    public long getTelefono() {
        return Telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public char getCategoria() {
        return categoria;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setTelefono(long Telefono) {
        this.Telefono = Telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCategoria(char categoria) {
        this.categoria = categoria;
    }
    
    
}

