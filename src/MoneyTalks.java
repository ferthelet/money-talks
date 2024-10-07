import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
// por favor cree una carpeta "lib" sin las comillas y agregue la dep json-20240303.jar
// podria necesitar cerrar el editor y cargar de nuevo para que funcione
import org.json.JSONObject;

public class MoneyTalks {
    private static final String API_KEY = "ad152da01910d2160641276c";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/";
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running) {

            System.out.println("\nBienvenido a Money Talks\n");
            System.out.println("1. Dolar =>> Peso argentino");
            System.out.println("2. Peso argentino =>> Dolar");
            System.out.println("3. Dolar =>> Real brasilenio");
            System.out.println("4. Real brasilenio =>> Dolar");
            System.out.println("5. Dolar =>> Peso colombiano");
            System.out.println("6. Peso colombiano =>> Dolar");
            System.out.println("7. Salir");
            System.out.print("\nSeleccione una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // para el newline

            switch (opcion) {
                case 1:
                    convertir("USD", "ARS");
                    break;
                case 2:
                    convertir("ARS", "USD");
                    break;
                case 3:
                    convertir("USD", "BRL");
                    break;
                case 4:
                    convertir("BRL", "USD");
                    break;
                case 5:
                    convertir("USD", "COP");
                    break;
                case 6:
                    convertir("COP", "USD");
                    break;
                case 7:
                    running = false;
                    System.out.println("Gracias por usar Money Talks. Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion invalida. Por fgavor, intente de nuevo.");
            }
            if (running) {
                System.out.println("Presione Enter para continuar...");
                scanner.nextLine(); // para el newline
            }
        }
    }

    private static void convertir(String monedaOrigen, String monedaDestino) throws Exception {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Ingrese la cantidad a convertir: ");
            double cantidad = scanner.nextDouble();

            JSONObject result = obtenerTasaDeCambio(monedaOrigen, monedaDestino, cantidad);

            if (result.getString("result").equals("success")) {
                double tasaDeCambio = result.getDouble("conversion_rate");
                double resultado = result.getDouble("conversion_result");
                String lastUpdateTime = result.getString("time_last_update_utc");

                System.out.printf("%.2f %s = %.2f %s. TC = %.2f. Actualizacion: %-22.22s GMT%n", 
                cantidad, monedaOrigen, resultado, monedaDestino, tasaDeCambio, lastUpdateTime);
            } else {
                System.out.println("Error al obtener la tasa de cambio: " + result.getString("error-type"));
            }
        } catch (Exception e) {
            System.out.println("Error al convertir la moneda: " + e.getMessage());
        }
    }

    private static JSONObject obtenerTasaDeCambio(String monedaOrigen, String monedaDestino, double cantidad) throws Exception {
        String cadenaURL = API_URL + monedaOrigen + "/" + monedaDestino + "/" + cantidad;
        URL url = new URL(cadenaURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Fallo : codigo de error HTTP : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String output;

        while ((output = br.readLine()) != null) {
            response.append(output);
        }
        conn.disconnect();
        return new JSONObject(response.toString());
    }
}
