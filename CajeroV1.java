// CajeroV1.java

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;
import java.io.FileReader;


public class CajeroV1 {


    /* Que vamos a hacer ahora:
    2 Opción 4 de menú para listar todas las cuentas de un cliente (se pide DNI)
3 Opción 5 de menú: Consulta todas las cuentas y da la información del cliente (información de
la cuenta y saldo y datos del cliente) cuyo saldo sea menor a una cantidad que se preguntará por consola.*/

    private static void escribirMovimiento(String movimiento) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("movimientos.txt", true))) {
            writer.write(movimiento);
            writer.newLine(); // Añade una nueva línea después de cada movimiento
            System.out.println("Movimiento registrado en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    private static void verMovimientos() {
        try (BufferedReader reader = new BufferedReader(new FileReader("movimientos.txt"))) {
            System.out.println("Movimientos registrados:");

            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    private static void borrarMovimientos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("movimientos.txt", false))) {
            // Sobrescribe el archivo con una cadena vacía
            writer.write("");
            System.out.println("Movimientos borrados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al borrar los movimientos: " + e.getMessage());
        }
    }
    public static void mostrarCuentasClientes(String dni){

    }
    public static void main(String[] args) {
        BigDecimal saldo = BigDecimal.valueOf(1000.0);
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            // Menú
            System.out.println("1- Retirar fondos");
            System.out.println("2- Ingresar fondos");
            System.out.println("3- Ver movimientos");
            System.out.println("0- Salir");
            System.out.print("Ingrese la opción deseada: ");
            
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la cantidad a retirar: ");
                    BigDecimal cantidadRetiro = BigDecimal.valueOf(scanner.nextDouble());

                    if (saldo.compareTo(cantidadRetiro) == -1) {
                        System.out.println("Saldo insuficiente. Su saldo actual es: " + saldo);
                    } else {
                        saldo = saldo.subtract(cantidadRetiro);
                        System.out.println("Retiro exitoso. Saldo restante: " + saldo);
                    }
                    escribirMovimiento("Retiro: " + cantidadRetiro);

                    break;

                case 2:
                    System.out.print("Ingrese la cantidad a ingresar: ");
                    BigDecimal cantidadIngreso = BigDecimal.valueOf(scanner.nextDouble());
                    if (cantidadIngreso.compareTo(BigDecimal.ZERO) == -1)
                    {
                        System.out.println("Operacion incorrecta introduce un numero positivo");
                    }
                    else {
                        saldo = saldo.add(cantidadIngreso);
                        System.out.println("Ingreso exitoso. Saldo actual: " + saldo);
                    }
                    escribirMovimiento("Ingreso: " + cantidadIngreso);
                    break;
                case 3:
                    verMovimientos();
                    break;
                case 4:
                    mostrarCuentasClientes("");
                    break;
                case 0:
                    borrarMovimientos();
                    System.out.println("Gracias por usar el cajero automático.");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción correcta.");
            }

        } while (opcion != 0);
        
        scanner.close();
    }
}
