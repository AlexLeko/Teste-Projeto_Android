package com.example.administrador.teste.model.services;


import com.example.administrador.teste.model.entities.ClientAddress;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public final class CepService {

    private static final String URL = "http://correiosapi.apphb.com/cep/"; // TEM QUE TERMINAR COM "/"

    private CepService(){
        super();
    }

    public static ClientAddress getAddressBy(String cep) {

        ClientAddress address = null;

        // CLIANDO UMA COMUNICAÇÃO REST - JSON
        try {
            // SCOPO DA CONEXAO COM A API.
            URL url = new URL(URL + cep);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/Json"); // OBRIGA O RETORNO  DE JSON.

            int responseCode = conn.getResponseCode();

            if(responseCode != HttpURLConnection.HTTP_OK){
                throw new RuntimeException("Error code: " +responseCode);
            }

            InputStream inputStream = conn.getInputStream();    // pega o body da requisição.

            // FAZ A REQUISIÇÃO COM O JACKSON.
            ObjectMapper objectMapper = new ObjectMapper();
            address = objectMapper.readValue(inputStream, ClientAddress.class);

            conn.disconnect();  // disconecta do servidor. Quando não é Stateless

        } catch (IOException e ) {
            e.printStackTrace();
        }

        return address;
    }

}
