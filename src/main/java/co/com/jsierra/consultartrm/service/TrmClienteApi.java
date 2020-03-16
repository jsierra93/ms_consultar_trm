package co.com.jsierra.consultartrm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class TrmClienteApi {
    private final WebClient webClient;
    private static final String API_MIME_TYPE = "application/json";
    private static final String API_BASE_URL = "http://app.docm.co/prod/Dmservices/Utilities.svc/GetTRM";
    private static final Logger logger = LoggerFactory.getLogger(TrmClienteApi.class);

    public TrmClienteApi() {
        this.webClient = WebClient.builder()
                .baseUrl(API_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, API_MIME_TYPE)
                .build();
    }

    public Flux<String> getTrmActual() {
        return webClient.get()
                .uri("/GetTRM")
                .exchange()
                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(String.class));
    }
}
