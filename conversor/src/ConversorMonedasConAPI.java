import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class ConversorMonedasConAPI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String apiKey = "64baafb2447fac74e0888dde";
        String[] monedas = {"USD", "BRL", "ARS", "EUR"};

        System.out.println("Seleccione la conversión deseada:");
        System.out.println("1. Peso Colombiano a Dólar");
        System.out.println("2. Peso Colombiano a Real (Brasil)");
        System.out.println("3. Peso Colombiano a Peso Argentino");
        System.out.println("4. Peso Colombiano a Euro");

        int opcion = sc.nextInt();
        System.out.println("Ingrese la cantidad en pesos colombianos:");
        double cantidad = sc.nextDouble();

        if (opcion >= 1 && opcion <= 4) {
            try {
                String monedaDestino = monedas[opcion - 1];
                double tasaCambio = obtenerTasaCambio(apiKey, monedaDestino);
                double resultado = cantidad * tasaCambio;
                System.out.println("La cantidad en " + monedaDestino + " es: " + resultado);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Opción no válida.");
        }
        sc.close();
    }

    public static double obtenerTasaCambio(String apiKey, String monedaDestino) throws Exception {
        String urlStr = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/COP";
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();

        JSONObject json = new JSONObject(content.toString());
        return json.getJSONObject("conversion_rates").getDouble(monedaDestino);
    }
}
