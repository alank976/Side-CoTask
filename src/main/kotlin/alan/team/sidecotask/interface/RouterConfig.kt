package alan.team.sidecotask.`interface`

import alan.team.sidecotask.domain.task.TaskHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.RequestPredicates.*
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class RouterConfig() {

    @Bean
    fun taskRoute(handler: TaskHandler): RouterFunction<ServerResponse> {
        return route(GET("/tasks"), HandlerFunction<ServerResponse>(handler::getAll))
                .andRoute(POST("/tasks").and(accept(APPLICATION_JSON)), HandlerFunction<ServerResponse>(handler::save))
                .andRoute(GET("/tasks/{id}"), HandlerFunction<ServerResponse>(handler::getOne))
                .andRoute(DELETE("/tasks/{id}"), HandlerFunction<ServerResponse>(handler::delete))
    }
}