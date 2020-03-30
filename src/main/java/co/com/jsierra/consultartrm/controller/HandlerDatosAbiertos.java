package co.com.jsierra.consultartrm.controller;

import co.com.jsierra.consultartrm.model.TrmLocalModel;
import co.com.jsierra.consultartrm.repository.TrmLocalRepository;
import co.com.jsierra.consultartrm.service.WebClientDatosGovApi;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class HandlerDatosAbiertos {

    TrmLocalRepository trmLocalRepository;
    WebClientDatosGovApi webClientDatosGovApi;


    public Mono<ServerResponse> obtenerTrmDatosAbiertosHoy(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(webClientDatosGovApi.getTrmActual(), String.class);
    }

    public Mono<ServerResponse> obtenerTrmApiDatosAbiertos(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(webClientDatosGovApi.getTrmHistorico(), String.class);
    }

    public Mono<ServerResponse> consultaBdLocal(ServerRequest request) {
        Flux<TrmLocalModel> local = trmLocalRepository.findAll();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(local, TrmLocalModel.class);
    }
}
