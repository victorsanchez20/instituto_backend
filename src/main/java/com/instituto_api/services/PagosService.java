package com.instituto_api.services;

import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.resources.preference.Preference;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PagosService {

    public String crearPago() throws Exception {

        PreferenceItemRequest item =
                PreferenceItemRequest.builder()
                        .title("Matrícula Instituto")
                        .quantity(1)
                        .currencyId("PEN")
                        .unitPrice(new BigDecimal("100.00"))
                        .build();

        PreferenceBackUrlsRequest backUrls =
                PreferenceBackUrlsRequest.builder()
                        .success("http://localhost:4200/pago/exito")
                        .failure("http://localhost:4200/pago/error")
                        .pending("http://localhost:4200/pago/pendiente")
                        .build();

        PreferenceRequest preferenceRequest =
                PreferenceRequest.builder()
                        .items(List.of(item))
                        .backUrls(backUrls)
                        .build();

        
        PreferenceClient client = new PreferenceClient();

    
        try {
            Preference preference = client.create(preferenceRequest);
            return preference.getInitPoint();

        } catch (MPApiException e) {

            System.out.println("STATUS: " + e.getStatusCode());
            System.out.println("RESPONSE: " + e.getApiResponse().getContent());

            return e.getApiResponse().getContent();
        }
    }
}