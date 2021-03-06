package co.com.jsierra.consultartrm.service;

import co.com.jsierra.consultartrm.model.TrmApiModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class WebClientDatosGovApi {
    private final WebClient webClient;
    private static final String API_MIME_TYPE = "application/json";
    private static final String API_DATOS_ABIERTOS = "https://www.datos.gov.co/resource/32sa-8pi3.json";

    /*
    respuesta de la API
   {
       "valor": "3941.92",
       "unidad": "COP",
       "vigenciadesde": "2020-03-14T00:00:00.000",
       "vigenciahasta": "2020-03-16T00:00:00.000"
   }
    */
    public WebClientDatosGovApi() {
        this.webClient = WebClient.builder()
                .baseUrl(API_DATOS_ABIERTOS)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, API_MIME_TYPE)
                .build();
    }

    public Flux<TrmApiModel> getTrmDay(LocalDate since, LocalDate until) {
        String parametros = "?vigenciadesde="+LocalDateTime.parse(since+"T00:00:00")+":00.000";
        parametros += "&vigenciahasta="+LocalDateTime.parse(until+"T00:00:00")+":00.000";
        return webClient.get()
                .uri(parametros)
                .retrieve()
                .bodyToFlux(TrmApiModel.class);
    }

    public Flux<TrmApiModel> getTrmHistorico() {
        return webClient.get()
                .exchange()
                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(TrmApiModel.class));
    }
}
