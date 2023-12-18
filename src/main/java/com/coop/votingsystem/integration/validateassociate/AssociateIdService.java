package com.coop.votingsystem.integration.validateassociate;


import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
@Service
public class AssociateIdService {

    public static boolean checkValidCpf(Long cpf) {
        String url = "https://run.mocky.io/v3/57f23672-c15f-48f8-90d3-d84ce00250b8/users/" + cpf;

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Verificação do status na resposta JSON
                String jsonResponse = response.toString();

                if (jsonResponse.contains("\"status\":\"ABLE_TO_VOTE\"")) {
                    return true;
                } else if (jsonResponse.contains("\"status\":\"UNABLE_TO_VOTE\"")) {
                    return false;
                } else {
                    System.out.println("Resposta inesperada: " + jsonResponse);
                    return false;
                }
            } else {
                System.out.println("Erro na requisição. Código de resposta: " + responseCode);
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
