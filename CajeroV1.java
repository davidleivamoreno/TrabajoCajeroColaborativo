// CajeroV1.java

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CajeroV1 {

    /* Que vamos a hacer ahora:
    2 Opción 4 de menú para listar todas las cuentas de un cliente (se pide DNI)
3 Opción 5 de menú: Consulta todas las cuentas y da la información del cliente (información de
la cuenta y saldo y datos del cliente) cuyo saldo sea menor a una cantidad que se preguntará por consola.*/


    private static final String URL = "jdbc:mysql://localhost:3306/cuentas_bancarias";
    private static final String USUARIO = "user";
    private static final String CONTRASENA = "user";


    // Método para establecer la conexión a la base de datos
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }

    // Ejemplo de método para realizar una consulta
    public static void consultarDatos() {
        String consultaSQL = "SELECT * FROM cuentas_bancarias.clientes"; //cambiar todo

        try (Connection conexion = obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(consultaSQL);
             ResultSet resultSet = statement.executeQuery()) {

            // Procesa los resultados
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                // Procesa otros campos según tu esquema de base de datos
                System.out.println("ID: " + id + ", Nombre: " + nombre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



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
    public static void mostrarClientesSaldo(BigDecimal d) {
        d.setScale(2);
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

            System.out.println("5- Mostrar clientes cuyo saldo sea inferior al introducido");
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
                case 5:
                    System.out.println("Introduce un Saldo máximo para filtrar");
                    BigDecimal cantidadFilt = BigDecimal.valueOf(scanner.nextDouble());
                    mostrarClientesSaldo(cantidadFilt);
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
