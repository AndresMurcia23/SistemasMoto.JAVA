/**
 * Clase para indicar el gestor de archivos
 */
package persistencia;

import empresa.motos.Motocicleta;
import java.io.File;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.ArrayList;

public class ArchivoMotocicletas {

    private File archivo;

    public ArchivoMotocicletas() {
        archivo = new File("motocicletas.txt");

    }

    public void agregar(Motocicleta moto) throws IOException //rw = read and write raf = random acces file
    {

        // Se verifica si el ID ya existe.
        if (archivo.exists() && archivo.length() > 0) {  // Se verifica que el archivo exista y tenga información guardada.
            if (buscar(moto.getId()) != null) {
                throw new IOException("El id ya existe");
            }
        }

        RandomAccessFile raf = new RandomAccessFile(archivo, "rw");

        // Se mueve al final del archivo.
        raf.seek(raf.length());

        // Se guardan los datos de la motocicleta.
        raf.writeInt(moto.getId());
        raf.writeUTF(moto.getPlaca());
        raf.writeUTF(moto.getMarca());
        raf.writeDouble(moto.getPrecio());
        raf.writeBoolean(moto.isDisponible());
        raf.writeChar(moto.getCategoria());
        raf.writeUTF(moto.getEstado());

        // Se cierra el archivo.
        raf.close();

        //METODOS QUE SIRVEN PARA escribir sobre el archivo y el segundo para hacer un salto de linea
    }

    public Motocicleta leer(long posicion) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(archivo, "r");

        raf.seek(posicion);

        int id = raf.readInt();
        String placa = raf.readUTF();
        String marca = raf.readUTF();
        double precio = raf.readDouble();
        boolean disponible = raf.readBoolean();
        char categoria = raf.readChar();
        String estado = raf.readUTF();

        raf.close();

        return new Motocicleta(
                id,
                placa,
                marca,
                precio,
                disponible,
                categoria,
                estado
        );

    }

    public Motocicleta buscar(int id) throws IOException {

        RandomAccessFile raf = new RandomAccessFile(archivo, "r");

        raf.seek(0);

        while (raf.getFilePointer() < raf.length()) {

            int idActual = raf.readInt();
            String placa = raf.readUTF();
            String marca = raf.readUTF();
            double precio = raf.readDouble();
            boolean disponible = raf.readBoolean();
            char categoria = raf.readChar();
            String estado = raf.readUTF();

            if (idActual == id) {

                raf.close();

                return new Motocicleta(
                        idActual,
                        placa,
                        marca,
                        precio,
                        disponible,
                        categoria,
                        estado
                );
            }
        }

        raf.close();

        return null;
    }

    public void eliminar(int id) throws IOException {

        File archivoTemporal = new File("motocicletas_temp.txt");

        RandomAccessFile raf = new RandomAccessFile(archivo, "r");
        RandomAccessFile temp = new RandomAccessFile(archivoTemporal, "rw");

        boolean encontrado = false;

        while (raf.getFilePointer() < raf.length()) {

            int idActual = raf.readInt();
            String placa = raf.readUTF();
            String marca = raf.readUTF();
            double precio = raf.readDouble();
            boolean disponible = raf.readBoolean();
            char categoria = raf.readChar();
            String estado = raf.readUTF();

            if (idActual == id) {

                encontrado = true;

            } else {

                temp.writeInt(idActual);
                temp.writeUTF(placa);
                temp.writeUTF(marca);
                temp.writeDouble(precio);
                temp.writeBoolean(disponible);
                temp.writeChar(categoria);
                temp.writeUTF(estado);
            }
        }

        raf.close();
        temp.close();

        if (encontrado) {

            archivo.delete();

            archivoTemporal.renameTo(archivo);

        } else {

            archivoTemporal.delete();
        }
    }

    public void actualizar(Motocicleta moto) throws IOException {

        File archivoTemporal = new File("motocicletas_temp.txt");

        RandomAccessFile raf = new RandomAccessFile(archivo, "r");
        RandomAccessFile temp = new RandomAccessFile(archivoTemporal, "rw");

        while (raf.getFilePointer() < raf.length()) {

            int idActual = raf.readInt();
            String placa = raf.readUTF();
            String marca = raf.readUTF();
            double precio = raf.readDouble();
            boolean disponible = raf.readBoolean();
            char categoria = raf.readChar();
            String estado = raf.readUTF();

            if (idActual == moto.getId()) {

                temp.writeInt(moto.getId());
                temp.writeUTF(moto.getPlaca());
                temp.writeUTF(moto.getMarca());
                temp.writeDouble(moto.getPrecio());
                temp.writeBoolean(moto.isDisponible());
                temp.writeChar(moto.getCategoria());
                temp.writeUTF(moto.getEstado());

            } else {

                temp.writeInt(idActual);
                temp.writeUTF(placa);
                temp.writeUTF(marca);
                temp.writeDouble(precio);
                temp.writeBoolean(disponible);
                temp.writeChar(categoria);
                temp.writeUTF(estado);
            }
        }

        raf.close();
        temp.close();

        archivo.delete();
        archivoTemporal.renameTo(archivo);
    }

    public ArrayList<Motocicleta> listar() throws IOException {

        ArrayList<Motocicleta> motos = new ArrayList<>();

        RandomAccessFile raf = new RandomAccessFile(archivo, "r");

        raf.seek(0);

        while (raf.getFilePointer() < raf.length()) {

            int id = raf.readInt();
            String placa = raf.readUTF();
            String marca = raf.readUTF();
            double precio = raf.readDouble();
            boolean disponible = raf.readBoolean();
            char categoria = raf.readChar();
            String estado = raf.readUTF();

            Motocicleta moto = new Motocicleta(
                    id,
                    placa,
                    marca,
                    precio,
                    disponible,
                    categoria,
                    estado
            );

            motos.add(moto);
        }

        raf.close();

        return motos;
    }

}
