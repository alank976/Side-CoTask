package alan.team.sidekotask.`interface`

import alan.team.sidekotask.domain.task.TaskHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

@Configuration
class RouterConfig {
    /**
     * https://github.com/mixitconf/mixit/blob/master/src/main/kotlin/mixit/web/routes/ApiRoutes.kt
     **/
    @Bean
    fun apiRouter(handler: TaskHandler) = router {
        "/api".nest {
            (accept(APPLICATION_JSON) and "/v1").nest {
                "/tasks".nest {
                    GET("/", handler::getAll)
                    POST("/", handler::save)
                    "/{id}".nest {
                        GET("/", handler::getOne)
                        DELETE("/", handler::delete)
                    }
                }
            }
        }
    }
}