package com.aluracursos.challenge2;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConversorAPI {
    private final String apiKey;

    public ConversorAPI(String apiKey) {
        this.apiKey = apiKey;
    }

    public double obtenerTasaCambio(String monedaOrigen, String monedaDestino) throws Exception {
        String urlStr = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + monedaOrigen + "/" + monedaDestino;
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
        return json.getDouble("conversion_rate");
    }
}
