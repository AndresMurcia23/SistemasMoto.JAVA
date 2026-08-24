/**
 * Clase para indicar el gestor de archivos
 */
package persistencia;

import empresa.motos.Motocicleta;
import java.io.File;
import java.io.RandomAccessFile;
import java.io.IOException;



public class ArchivoMotocicletas {
    
    private File archivo;

    public ArchivoMotocicletas() 
    {
        archivo = new File("motocicletas.txt");

    }
    public void agregar(Motocicleta moto) throws IOException 
    {
    
    
    }
    
}
