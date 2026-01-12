package dominio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DistanciaImp {

    private static final String URL_API_DISTANCIA =
            "http://sublimas.com.mx:8080/calculadora/api/envios/distancia/";

    public static double obtenerDistancia(String origen, String destino) throws Exception {

        if (origen == null || origen.isEmpty() || destino == null || destino.isEmpty()) {
            throw new IllegalArgumentException("Origen y destino son obligatorios");
        }

        String urlString = URL_API_DISTANCIA + origen + "," + destino;
        URL url = new URL(urlString);

        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        conexion.setConnectTimeout(5000);
        conexion.setReadTimeout(5000);

        int codigoRespuesta = conexion.getResponseCode();

        if (codigoRespuesta != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException(
                    "Error al consultar API de distancia. Código HTTP: " + codigoRespuesta
            );
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conexion.getInputStream())
        );

        StringBuilder respuesta = new StringBuilder();
        String linea;

        while ((linea = reader.readLine()) != null) {
            respuesta.append(linea);
        }

        reader.close();
        conexion.disconnect();

        return Double.parseDouble(respuesta.toString());
    }
}