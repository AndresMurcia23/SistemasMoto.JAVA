package pruebas;

import empresa.motos.Motocicleta;
import persistencia.ArchivoMotocicletas;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Motocicleta moto = new Motocicleta();

        moto.setId(1);
        moto.setPlaca("ABC12F");
        moto.setMarca("AKT");
        moto.setCategoria('D');
        moto.setPrecio(8500000);
        moto.setDisponible(true);
        moto.setEstado("ACTIVO");

        ArchivoMotocicletas archivo = new ArchivoMotocicletas();

        try {
            archivo.agregar(moto);

            System.out.println("Motocicleta guardada correctamente.");

            Motocicleta motoLeida = archivo.leer(0);

            System.out.println("ID: " + motoLeida.getId());
            System.out.println("Placa: " + motoLeida.getPlaca());
            System.out.println("Marca: " + motoLeida.getMarca());
            System.out.println("Categoría: " + motoLeida.getCategoria());
            System.out.println("Precio: " + motoLeida.getPrecio());
            System.out.println("Disponible: " + motoLeida.isDisponible());
            System.out.println("Estado: " + motoLeida.getEstado());

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
