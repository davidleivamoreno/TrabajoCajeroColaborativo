// CajeroV1.java

import java.math.BigDecimal;
import java.util.Scanner;

public class CajeroV1 {
    public static void main(String[] args) {
        BigDecimal saldo = BigDecimal.valueOf(1000.0);
        //double saldo = 1000.0;
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
                    BigDecimal cantidadRetiro = BigDecimal.valueOf(scanner.nextDouble());

                    if (cantidadRetiro.compareTo(saldo) == -1) {
                        System.out.println("Saldo insuficiente. Su saldo actual es: " + saldo);
                    } else {
                        saldo = saldo.subtract(cantidadRetiro);
                        System.out.println("Retiro exitoso. Saldo restante: " + saldo);
                    }
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
