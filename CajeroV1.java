// CajeroV1.java

import java.util.Scanner;

public class CajeroV1 {
    public static void main(String[] args) {
        double saldo = 1000.0;
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            // Menú
            System.out.println("1- Retirar fondos");
            System.out.println("2- Ingresar fondos");
            System.out.println("0- Salir");
            System.out.print("Ingrese la opción deseada: ");
            
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la cantidad a retirar: ");
                    double cantidadRetiro = scanner.nextDouble();

                    if (cantidadRetiro > saldo) {
                        System.out.println("Saldo insuficiente. Su saldo actual es: " + saldo);
                    } else {
                        saldo -= cantidadRetiro;
                        System.out.println("Retiro exitoso. Saldo restante: " + saldo);
                    }
                    break;

                case 2:
                    System.out.print("Ingrese la cantidad a ingresar: ");
                    double cantidadIngreso = scanner.nextDouble();
                    saldo += cantidadIngreso;
                    System.out.println("Ingreso exitoso. Saldo actual: " + saldo);
                    break;

                case 0:
                    System.out.println("Gracias por usar el cajero automático.");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción correcta.");
            }

        } while (opcion != 0);
        
        scanner.close();
    }
}
