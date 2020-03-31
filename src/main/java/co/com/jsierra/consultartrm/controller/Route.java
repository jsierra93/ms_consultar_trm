package co.com.jsierra.consultartrm.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Route {

    private static final String ROUTE_CONSULT_TODAY_BD = "/consult/today";
    private static final String ROUTE_CONSULT_HISTORY_BD = "/consult/history";
    private static final String ROUTE_RESET_HISTORY_BD = "/reset/history";
    private static final String ROUTE_SYNC_HISTORY_BD_API = "/sync/history";
    private static final String ROUTE_DELETE_REPEAT_BD = "/delete/repeat";
    private static final String ROUTE_LOAD_MANUAL_BD = "/load/manual";

    @Bean
    RouterFunction<ServerResponse> routes(Handler handler) {
        return route(GET(ROUTE_CONSULT_TODAY_BD).and(accept(MediaType.APPLICATION_JSON)), handler::consultToday)
                .andRoute(GET(ROUTE_CONSULT_HISTORY_BD).and(accept(MediaType.APPLICATION_JSON)), handler::consultHistory)
                .andRoute(DELETE(ROUTE_RESET_HISTORY_BD).and(accept(MediaType.APPLICATION_JSON)), handler::resetDataBase)
                .andRoute(GET(ROUTE_SYNC_HISTORY_BD_API).and(accept(MediaType.APPLICATION_JSON)), handler::syncDataBaseFromApi)
                .andRoute(POST(ROUTE_LOAD_MANUAL_BD).and(accept(MediaType.APPLICATION_JSON)), handler::loadDataManual)
                .andRoute(GET(ROUTE_DELETE_REPEAT_BD).and(accept(MediaType.APPLICATION_JSON)), handler::deleteDataRepeat)
                ;
    }
}
