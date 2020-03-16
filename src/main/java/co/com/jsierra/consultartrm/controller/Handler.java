package co.com.jsierra.consultartrm.controller;

import co.com.jsierra.consultartrm.model.Trm;
import co.com.jsierra.consultartrm.repository.TrmRepository;
import co.com.jsierra.consultartrm.service.TrmClienteApi;
import co.com.jsierra.consultartrm.service.WebClientDatosGovApi;
import com.sun.jna.platform.win32.WinCrypt;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@AllArgsConstructor
@Component
public class Handler {

    TrmRepository trmRepository;
    TrmClienteApi trmClienteApi;
    WebClientDatosGovApi webClientDatosGovApi;

    public Mono<ServerResponse> trmHoy(ServerRequest request) {
        LocalDate date = LocalDate.now();
        Trm prueba = Trm.builder().id("5e6ea031118f652d7a4b275").fecha(date).valor("3000.00").build();
        Flux<Trm> trmActual = trmRepository.findAll()
                .filter(val -> val.getFecha().equals(date))
                .switchIfEmpty(
                        Flux.just(prueba)
                );
            return ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(trmActual, Trm.class);
    }

    public Mono<ServerResponse> trmFecha(ServerRequest request) {
        Mono<Trm> trmActual = trmRepository.findById(request.pathVariable("id"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(trmActual, Trm.class);
    }

    public Mono<ServerResponse> obtenerTrmApi(ServerRequest request) {
        Flux<String> trm = trmClienteApi.getTrmActual();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(trm , String.class);
    }

    public Mono<ServerResponse> obtenerTrmDatosAbiertosHoy(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(webClientDatosGovApi.getTrmActual(), String.class);
    }

    public Mono<ServerResponse> obtenerTrmApiDatosAbiertos(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(webClientDatosGovApi.getTrmHistorico() , String.class);
    }

}
