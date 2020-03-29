package co.com.jsierra.consultartrm.controller;

import co.com.jsierra.consultartrm.model.Trm;
import co.com.jsierra.consultartrm.model.TrmLocal;
import co.com.jsierra.consultartrm.repository.TrmLocalRepository;
import co.com.jsierra.consultartrm.service.TrmClienteApi;
import co.com.jsierra.consultartrm.service.WebClientDatosGovApi;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@AllArgsConstructor
@Component
public class HandlerApi {

    TrmLocalRepository trmLocalRepository;
    TrmClienteApi trmClienteApi;
    WebClientDatosGovApi webClientDatosGovApi;

    public Mono<ServerResponse> consultToday(ServerRequest request) {
        LocalDate date = LocalDate.now();
        TrmLocal prueba = TrmLocal.builder().id("5e6ea031118f652d7a4b275").vigenciadesde(date.toString()).valor("3000.00").build();
        Flux<TrmLocal> trmActual = trmLocalRepository.findAll()
                .filter(val -> val.getVigenciadesde().equals(date))
                .switchIfEmpty(
                        Flux.just(prueba)
                );
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(trmActual, Trm.class);
    }

    public Mono<ServerResponse> consultHistory(ServerRequest request) {
        Flux<TrmLocal> trmActual = trmLocalRepository.findAll();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Flux.just(trmActual), Trm.class);
    }

}
