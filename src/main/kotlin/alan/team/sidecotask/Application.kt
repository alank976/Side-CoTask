package alan.team.sidecotask

import org.springframework.boot.SpringApplication
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication
@EnableWebFlux
class Application {
    fun main(args: Array<String>) {
        val app = SpringApplication(Application::class.java)
        app.webApplicationType = WebApplicationType.REACTIVE
        app.run(*args)
//    runApplication<SidecotaskApplication>(*args)
    }
}