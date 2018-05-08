package alan.team.sidecotask

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Application

fun main(args: Array<String>) {
//    val app = SpringApplication(Application::class.java)
//    app.webApplicationType = WebApplicationType.REACTIVE
//    app.run(*args)
    runApplication<Application>(*args)
}
