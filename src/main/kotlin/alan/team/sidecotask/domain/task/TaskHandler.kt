package alan.team.sidecotask.domain.task

import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate
import javax.annotation.PostConstruct

@Component
class TaskHandler(val repository: TaskRepository) {
    @PostConstruct
    fun init() {
        val task = Task(1, LocalDate.now(), "Alan", "Something", 2.0)
        repository.save(task).subscribe()
    }

    fun getAll(request: ServerRequest) : Mono<ServerResponse> {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(repository.findAll(), Task::class.java)
    }


}