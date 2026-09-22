/**
 * Clase para indicar el gestor de archivos
 */
package persistencia;

import empresa.motos.Motocicleta;
import java.io.File;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.rmi.server.ExportException;
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
        Archivoconsesionario archivoconsesionario = new Archivoconsesionario();

        if (archivoconsesionario.buscarcs(moto.getCodigoConcesionario()) == null) {
            throw new ExportException("El Concesionario No existe");

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
        raf.writeInt(moto.getCodigoConcesionario());

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
        int codigoConcesionario = raf.readInt();

        raf.close();

        return new Motocicleta(
                id,
                placa,
                marca,
                precio,
                disponible,
                categoria,
                estado,
                codigoConcesionario
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
            int codigoConcesionario = raf.readInt();

            if (idActual == id) {

                raf.close();

                return new Motocicleta(
                        idActual,
                        placa,
                        marca,
                        precio,
                        disponible,
                        categoria,
                        estado,
                        codigoConcesionario
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
            int codigoConcesionario = raf.readInt();

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
                temp.writeInt(codigoConcesionario);
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

    public void actualizar(int idOriginal, Motocicleta moto) throws IOException {

        Archivoconsesionario archivoConcesionario = new Archivoconsesionario();

        if (archivoConcesionario.buscarcs(moto.getCodigoConcesionario()) == null) {
            throw new IOException("El concesionario no existe");
        }

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
            int codigoConcesionario = raf.readInt();

            if (idActual == idOriginal) {

                temp.writeInt(moto.getId());
                temp.writeUTF(moto.getPlaca());
                temp.writeUTF(moto.getMarca());
                temp.writeDouble(moto.getPrecio());
                temp.writeBoolean(moto.isDisponible());
                temp.writeChar(moto.getCategoria());
                temp.writeUTF(moto.getEstado());
                temp.writeInt(moto.getCodigoConcesionario());

            } else {

                temp.writeInt(idActual);
                temp.writeUTF(placa);
                temp.writeUTF(marca);
                temp.writeDouble(precio);
                temp.writeBoolean(disponible);
                temp.writeChar(categoria);
                temp.writeUTF(estado);
                temp.writeInt(codigoConcesionario);
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
            int codigoConcesionario = raf.readInt();

            Motocicleta moto = new Motocicleta(
                    id,
                    placa,
                    marca,
                    precio,
                    disponible,
                    categoria,
                    estado,
                    codigoConcesionario
            );

            motos.add(moto);
        }

        raf.close();

        return motos;
    }

    public double calcularPrecios() throws IOException {

        double suma = 0;

        RandomAccessFile raf = new RandomAccessFile(archivo, "r");

        while (raf.getFilePointer() < raf.length()) {

            raf.readInt();
            raf.readUTF();
            raf.readUTF();

            double precio = raf.readDouble();

            raf.readBoolean();
            raf.readChar();
            raf.readUTF();
            raf.readInt();

            suma = suma + precio;
        }

        raf.close();

        return suma;

    }

    public boolean existePorConcesionario(int codigoConcesionario) throws IOException {

        if (!archivo.exists()) {
            return false;
        }

        RandomAccessFile raf = new RandomAccessFile(archivo, "r");

        raf.seek(0);

        while (raf.getFilePointer() < raf.length()) {

            raf.readInt();
            raf.readUTF();
            raf.readUTF();
            raf.readDouble();
            raf.readBoolean();
            raf.readChar();
            raf.readUTF();

            int codigo = raf.readInt();

            if (codigo == codigoConcesionario) {

                raf.close();
                return true;
            }
        }

        raf.close();
        return false;
    }

    public double calcularMotos() throws IOException {

        double suma = 0;

        RandomAccessFile raf = new RandomAccessFile(archivo, "r");

        while (raf.getFilePointer() < raf.length()) {

            raf.readInt();
            raf.readUTF();
            raf.readUTF();

            double precio = raf.readDouble();

            raf.readBoolean();
            raf.readChar();
            raf.readUTF();
            raf.readInt();

            suma = suma + 1;
        }

        return suma;
    }

}
