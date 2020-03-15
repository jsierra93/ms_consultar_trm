package co.com.jsierra.consultartrm.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Route {
    private static final String ROUTE_CONSULT = "/trmhoy";
    private static final String ROUTE_CONSULT_ID = "/trmfecha/{fecha}";

    @Bean
    RouterFunction<ServerResponse> routes(Handler handler) {
        return route(GET(ROUTE_CONSULT).and(accept(MediaType.APPLICATION_JSON)), handler::trmHoy)
                .andRoute(
                GET(ROUTE_CONSULT_ID).and(accept(MediaType.APPLICATION_JSON)), handler::trmFecha
        );
    }
}
