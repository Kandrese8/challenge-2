import com.aluracursos.challenge2.ConversorAPI;
import com.aluracursos.challenge2.ConversorMonedas;

import java.text.DecimalFormat;
import java.util.Scanner;

public class AplicacionConversor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ConversorAPI api = new ConversorAPI("64baafb2447fac74e0888dde");
        boolean continuar = true;

        while (continuar) {
            System.out.println("Seleccione la conversión deseada:");
            System.out.println("1. Dólar a Peso Colombiano");
            System.out.println("2. Peso Colombiano a Dólar");
            System.out.println("3. Dólar a Peso Argentino");
            System.out.println("4. Peso Argentino a Dólar");
            System.out.println("5. Dólar a Real Brasileño");
            System.out.println("6. Real Brasileño a Dólar");
            System.out.println("7. SALIR");

            int opcion = sc.nextInt();

            if (opcion == 7) {
                System.out.println("Programa finalizado.");
                break;
            }

            System.out.println("Ingrese la cantidad:");
            double cantidad = sc.nextDouble();

            try {
                String monedaOrigen = "";
                String monedaDestino = "";

                switch (opcion) {
                    case 1: monedaOrigen = "USD"; monedaDestino = "COP"; break;
                    case 2: monedaOrigen = "COP"; monedaDestino = "USD"; break;
                    case 3: monedaOrigen = "USD"; monedaDestino = "ARS"; break;
                    case 4: monedaOrigen = "ARS"; monedaDestino = "USD"; break;
                    case 5: monedaOrigen = "USD"; monedaDestino = "BRL"; break;
                    case 6: monedaOrigen = "BRL"; monedaDestino = "USD"; break;
                    default:
                        System.out.println("Opción no válida.");
                        continue;
                }

                ConversorMonedas conversor = new ConversorMonedas(monedaOrigen, monedaDestino, cantidad);
                double tasaCambio = api.obtenerTasaCambio(monedaOrigen, monedaDestino);
                double resultado = conversor.realizarConversion(tasaCambio);

                // Formatear el resultado
                if (monedaDestino.equals("COP")) {
                    DecimalFormat df = new DecimalFormat("#,###");
                    System.out.println("La cantidad convertida de " + monedaOrigen + " a " + monedaDestino + " es: " + df.format(resultado));
                } else {
                    System.out.println("La cantidad convertida de " + monedaOrigen + " a " + monedaDestino + " es: " + resultado);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        sc.close();
    }
}