/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import empresa.motos.Concesionario;
import java.io.File;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author bloqu
 */
public class Archivoconsesionario {

    private File archivo;
    private int idOriginal;

    public Archivoconsesionario() {
        archivo = new File("Consesionario.txt");
    }

    public void agregarcs(Concesionario cose) throws IOException //rw = read and write raf = random acces file
    {

        // Se verifica si el Codigo ya existe.
        if (archivo.exists() && archivo.length() > 0) {  // Se verifica que el archivo exista y tenga información guardada.
            if (buscarcs(cose.getCodigo()) != null) {
                throw new IOException("El codigo ya existe");
            }
        }

        RandomAccessFile raf = new RandomAccessFile(archivo, "rw");

        raf.seek(raf.length());

        // Se guardan los datos del concesionario.
        raf.writeInt(cose.getCodigo());
        raf.writeLong(cose.getTelefono());
        raf.writeUTF(cose.getDireccion());
        raf.writeChar(cose.getCategoria());
        raf.writeDouble(cose.getPresupuesto());

        raf.close();

    }

    public Concesionario buscarcs(int Codigo) throws IOException {

        RandomAccessFile raf = new RandomAccessFile(archivo, "r");

        raf.seek(0);

        while (raf.getFilePointer() < raf.length()) {

            int CodigoActual = raf.readInt();
            long telefono = raf.readLong();
            String Direccion = raf.readUTF();
            char categoria = raf.readChar();
            double presupuesto = raf.readDouble();

            if (CodigoActual == Codigo) {
                raf.close();
                return new Concesionario(CodigoActual, telefono, Direccion, categoria, presupuesto);
            }
        }
        raf.close();
        return null;
    }

    public Concesionario leercs(long posicion) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(archivo, "r");

        raf.seek(posicion);

        int Codigo = raf.readInt();
        long telefono = raf.readLong();
        String Direccion = raf.readUTF();
        char categoria = raf.readChar();
        double presupuesto = raf.readDouble();

        raf.close();

        return new Concesionario(Codigo, telefono, Direccion, categoria, presupuesto);

    }

    public ArrayList<Concesionario> listarcs() throws IOException {

        ArrayList<Concesionario> motos = new ArrayList<>();

        RandomAccessFile raf = new RandomAccessFile(archivo, "r");

        raf.seek(0);

        while (raf.getFilePointer() < raf.length()) {

            int codigo = raf.readInt();
            long telefono = raf.readLong();
            String Direccion = raf.readUTF();
            char categoria = raf.readChar();
            double presupuesto = raf.readDouble();

            Concesionario cose = new Concesionario(
                    codigo,
                    telefono,
                    Direccion,
                    categoria,
                    presupuesto
            );

            motos.add(cose);
        }

        raf.close();

        return motos;
    }

    public void eliminarcs(int codigo) throws IOException {

        ArchivoMotocicletas archivoMotocicletas = new ArchivoMotocicletas();

        if (archivoMotocicletas.existePorConcesionario(codigo)) {
            throw new IOException("No se puede eliminar el concesionario porque tiene motocicletas asociadas");
        }

        File archivoTemporal = new File("Consesionario_temp.txt");

        RandomAccessFile raf = new RandomAccessFile(archivo, "r");
        RandomAccessFile temp = new RandomAccessFile(archivoTemporal, "rw");

        boolean encontrado = false;

        while (raf.getFilePointer() < raf.length()) {

            int CodigoActual = raf.readInt();
            long telefono = raf.readLong();
            String Direccion = raf.readUTF();
            char categoria = raf.readChar();
            double presupuesto = raf.readDouble();

            if (CodigoActual == codigo) {

                encontrado = true;

            } else {

                temp.writeInt(CodigoActual);
                temp.writeLong(telefono);
                temp.writeUTF(Direccion);
                temp.writeChar(categoria);
                temp.writeDouble(presupuesto);

            }
        }

        raf.close();
        temp.close();

        archivo.delete();
        archivoTemporal.renameTo(archivo);
    }

    public void actualizarcs(int idOriginal, Concesionario cose) throws IOException {

        if (idOriginal != cose.getCodigo()) {

            ArchivoMotocicletas archivoMotos = new ArchivoMotocicletas();

            if (archivoMotos.existePorConcesionario(idOriginal)) {
                throw new IOException("No se puede cambiar el codigo porque el concesionario tiene motocicletas asociadas");
            }

            if (buscarcs(cose.getCodigo()) != null) {
                throw new IOException("El codigo ya existe");
            }
        }

        File archivoTemporal = new File("Concesionario_temp.txt");

        RandomAccessFile raf = new RandomAccessFile(archivo, "r");
        RandomAccessFile temp = new RandomAccessFile(archivoTemporal, "rw");

        boolean encontrado = false;

        while (raf.getFilePointer() < raf.length()) {

            int CodigoActual = raf.readInt();
            long telefono = raf.readLong();
            String Direccion = raf.readUTF();
            char categoria = raf.readChar();
            double presupuesto = raf.readDouble();

            if (CodigoActual == idOriginal) {

                temp.writeInt(cose.getCodigo());
                temp.writeLong(cose.getTelefono());
                temp.writeUTF(cose.getDireccion());
                temp.writeChar(cose.getCategoria());
                temp.writeDouble(cose.getPresupuesto());

                encontrado = true;

            } else {

                temp.writeInt(CodigoActual);
                temp.writeLong(telefono);
                temp.writeUTF(Direccion);
                temp.writeChar(categoria);
                temp.writeDouble(presupuesto);
            }
        }

        raf.close();
        temp.close();

        if (encontrado) {
            archivo.delete();
            archivoTemporal.renameTo(archivo);
        }
    }

    public double calcularPresupuesto() throws IOException {

        double suma = 0;

        RandomAccessFile raf = new RandomAccessFile(archivo, "r");

        while (raf.getFilePointer() < raf.length()) {

            raf.readInt();
            raf.readLong();
            raf.readUTF();
            raf.readChar();

            double presupuesto = raf.readDouble();

            suma = suma + presupuesto;
        }

        raf.close();

        return suma;
    }
}
