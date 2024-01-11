El proyecto tiene como jdk el JDK21.
Acciones del Programador Colaborador (Cajero v2,osea tu parte Ismael):
Darío se encarga de la v3

Fork del Repositorio y Clonación:

Haces un fork del repositorio del programador principal en tu cuenta del GitHub.
Clonas el repositorio en tu máquina local.


Añade una nueva opción al menú (opción 3) para la consulta de movimientos.
Implementa la escritura y lectura de movimientos en el archivo "movimientos.txt".
Usa las clases FileWriter,FileReader,BufferedWriter y BufferedReader como por ejemplo asi:
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscribirEnArchivo {
    public static void main(String[] args) {
        String contenido = "Hola, esto es un ejemplo.";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("archivo.txt"))) {
            // Escribir en el archivo
            writer.write(contenido);
            System.out.println("Contenido escrito en el archivo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
Y asi se hacia para leer desde un archivo:
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeerDesdeArchivo {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("archivo.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Imprimir cada línea leída desde el archivo
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


Modifica las opciones 1 y 2 para reflejar los cambios en el archivo de movimientos.
Commit y Push del Cajero v2:

Realiza commits con los cambios en el código y la incorporación de la funcionalidad de consulta de movimientos.
Haces push de los cambios a mi repositorio en GitHub(donde te he agregado como colaborador :) ).
