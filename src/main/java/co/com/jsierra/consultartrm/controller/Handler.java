package co.com.jsierra.consultartrm.controller;

import co.com.jsierra.consultartrm.model.TrmApiModel;
import co.com.jsierra.consultartrm.model.TrmLocalModel;
import co.com.jsierra.consultartrm.repository.TrmLocalRepository;
import co.com.jsierra.consultartrm.service.WebClientDatosGovApi;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static co.com.jsierra.consultartrm.utilities.UtilitiesManager.*;

@AllArgsConstructor
@Component
public class Handler {
    private static final Logger LOGGER = LoggerFactory.getLogger(Handler.class);

    TrmLocalRepository trmLocalRepository;
    WebClientDatosGovApi webClientDatosGovApi;

    public Mono<ServerResponse> consultHistory(ServerRequest request) {
        Flux<TrmLocalModel> trmActual = trmLocalRepository.findAll();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(trmActual, TrmLocalModel.class);
    }

    public Mono<ServerResponse> consultToday(ServerRequest request) {
        //  LocalDate dayToday = LocalDate.of(2020, 03, 04); // para simular fechas
        LocalDate dayToday = LocalDate.now();
        LocalDate daySince = dayToday;
        LocalDate dayUntil = dayToday;

        boolean workingDay = workingDay(dayToday);

        if (!workingDay) {
            daySince = lastWorkingDay(dayToday);
            dayUntil = nextWorkingDay(dayToday);
        }

        Flux<TrmLocalModel> data = trmLocalRepository.findBySinceOrUntil(daySince.toString(), dayUntil.toString())
                .switchIfEmpty(
                        webClientDatosGovApi.getTrmDay(daySince, dayUntil)
                                .flatMap(val -> {
                                    return trmLocalRepository.save(TrmLocalModel.builder().code(val.getUnidad()).value(val.getValor()).since(val.getVigenciadesde().toLocalDateTime().toLocalDate().plusDays(1).toString()).until(val.getVigenciahasta().toLocalDateTime().toLocalDate().plusDays(1).toString()).build());
                                })
                );

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(data, TrmLocalModel.class);
    }

    public Mono<ServerResponse> resetDataBase(ServerRequest request) {
        Mono<Long> count = trmLocalRepository.findAll().count();
        count.subscribe(
                cant -> System.out.println("Registros eliminados:" + cant)
        );
        Mono<Void> delete = trmLocalRepository.deleteAll();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(delete, Void.class);
    }

    public Mono<ServerResponse> syncDataBaseFromApi(ServerRequest request) {
        /**
         * Pendiente de eliminar u omitir fechas repetidas
         */

        Flux<TrmLocalModel> dataFromApi = webClientDatosGovApi.getTrmHistorico()
                .flatMap(
                        trmDay -> {
                            return trmLocalRepository.save(TrmLocalModel.builder().code(trmDay.getUnidad()).value(trmDay.getValor()).since(trmDay.getVigenciadesde().toLocalDateTime().toLocalDate().plusDays(1).toString()).until(trmDay.getVigenciahasta().toLocalDateTime().toLocalDate().plusDays(1).toString()).build());
                        }
                );

        dataFromApi.count()
        .subscribe(
                x -> System.out.println("Cantidad de registros: "+x)
        );

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(dataFromApi, TrmLocalModel.class);
    }

    public Mono<ServerResponse> loadDataManual(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just("Se han cargado N registros"), String.class);
    }
}
