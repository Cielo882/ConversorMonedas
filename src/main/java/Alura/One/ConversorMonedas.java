import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ConversorMonedas extends JFrame {
    private JTextField cantidadField;
    private JComboBox<String> monedaOrigenBox;
    private JComboBox<String> monedaDestinoBox;
    private JLabel resultadoLabel;

    private static final String API_KEY = "5e1015f40cbf6a72be410cb6"; // Reemplaza con tu API Key
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public ConversorMonedas() {
        setTitle("Conversor de Monedas");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        // Componentes de la GUI
        JLabel cantidadLabel = new JLabel("Cantidad:");
        cantidadField = new JTextField();

        JLabel monedaOrigenLabel = new JLabel("Moneda Origen:");
        monedaOrigenBox = new JComboBox<>(new String[]{"USD", "MXN", "EUR", "GBP"});

        JLabel monedaDestinoLabel = new JLabel("Moneda Destino:");
        monedaDestinoBox = new JComboBox<>(new String[]{"USD", "MXN", "EUR", "GBP"});

        JButton convertirButton = new JButton("Convertir");
        resultadoLabel = new JLabel("Resultado:");

        // Añadir los componentes a la ventana
        add(cantidadLabel);
        add(cantidadField);
        add(monedaOrigenLabel);
        add(monedaOrigenBox);
        add(monedaDestinoLabel);
        add(monedaDestinoBox);
        add(convertirButton);
        add(resultadoLabel);

        // Acción del botón de conversión
        convertirButton.addActionListener(e -> {
            try {
                convertirMoneda();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    private void convertirMoneda() throws IOException {
        String monedaOrigen = (String) monedaOrigenBox.getSelectedItem();
        String monedaDestino = (String) monedaDestinoBox.getSelectedItem();
        double cantidad = Double.parseDouble(cantidadField.getText());

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL + monedaOrigen)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string();
                JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
                JsonObject tasas = jsonObject.getAsJsonObject("conversion_rates");
                double tasaCambio = tasas.get(monedaDestino).getAsDouble();

                // Realizar la conversión
                double resultado = cantidad * tasaCambio;
                resultadoLabel.setText(String.format("Resultado: %.2f %s", resultado, monedaDestino));
            } else {
                resultadoLabel.setText("Error al obtener los datos");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConversorMonedas frame = new ConversorMonedas();
            frame.setVisible(true);
        });
    }
}

