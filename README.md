# Conversor de Monedas en Java

Esta aplicación es un conversor de monedas desarrollado en Java, con una interfaz gráfica (GUI) utilizando Swing. Utiliza la API de tasas de cambio [Exchange Rate API](https://www.exchangerate-api.com/) para obtener los tipos de cambio actuales y realizar conversiones entre distintas monedas. 

## Características

- Conversión entre monedas: USD, MXN, EUR, GBP (puedes agregar más).
- Interfaz gráfica interactiva creada con Swing.
- Conexión en tiempo real con la API de tasas de cambio.
- Resultado inmediato de la conversión basado en la cantidad ingresada y las monedas seleccionadas.

## Tecnologías utilizadas

- **Java**: Lenguaje de programación principal.
- **Swing**: Biblioteca para crear la interfaz gráfica (GUI).
- **OkHttp**: Librería para realizar solicitudes HTTP a la API.
- **Gson**: Librería para parsear los datos JSON recibidos de la API.

## Requisitos

- Java 8 o superior.
- Dependencias externas (si no usas un gestor de dependencias como Maven):
  - [OkHttp](https://square.github.io/okhttp/)
  - [Gson](https://github.com/google/gson)
- **API Key** de [Exchange Rate API](https://www.exchangerate-api.com/) para hacer las solicitudes de tasas de cambio.

## Instalación

1. Clona este repositorio:

```bash
git clone https://github.com/tu_usuario/conversor_monedas_java.git
