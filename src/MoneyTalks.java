import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class MoneyTalks {
    private static final String API_KEY = "ad152da01910d2160641276c";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";
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
            double tasaDeCambio = obtenerTasaDeCambio(monedaOrigen, monedaDestino);
            Scanner scanner = new Scanner(System.in);

            System.out.println("Ingrese la cantidad a convertir: ");
            double cantidad = scanner.nextDouble();

            double resultado = cantidad * tasaDeCambio;

            System.out.printf("%.2f %s = %.2f %s%n", cantidad, monedaOrigen, resultado, monedaDestino);
        } catch (Exception e) {
            System.out.println("Error al convertir la moneda: " + e.getMessage());
        }
    }

    private static double obtenerTasaDeCambio(String monedaOrigen, String monedaDestino) throws Exception {
        String cadenaURL = API_URL + monedaOrigen;
        URL url = new URL(cadenaURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader lector = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder respuesta = new StringBuilder();
        String cadena;

        while ((cadena = lector.readLine()) != null) {
            respuesta.append(cadena);
        }
        lector.close();

        JSONObject jsonObject = new JSONObject(respuesta.toString());
        JSONObject tasaDeCambio = jsonObject.getJSONObject("conversion_rates");

        return tasaDeCambio.getDouble(monedaDestino);
    }
}
