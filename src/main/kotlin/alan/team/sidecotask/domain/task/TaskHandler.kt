package alan.team.sidecotask.domain.task

import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.net.URI

@Component
class TaskHandler(val repository: TaskRepository) {
    fun getAll(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(repository.findAll(), Task::class.java)
    }

    fun getOne(request: ServerRequest): Mono<ServerResponse> {
        val id = Integer.parseInt(request.pathVariable("id"))
        return repository.findById(id)
                .flatMap { t ->
                    ServerResponse.ok().body(Mono.just(t), Task::class.java)
                }
    }

    fun save(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(Task::class.java)
                .flatMap { t -> repository.save(t) }
                .flatMap { t ->
                    ServerResponse.created(URI.create("/tasks/" + t.id))
                            .body(Mono.just(t), Task::class.java)
                }
    }

    fun delete(request: ServerRequest): Mono<ServerResponse> {
        val id = Integer.parseInt(request.pathVariable("id"))
        return repository.deleteById(id)
                .flatMap { ServerResponse.ok().build() }
                .switchIfEmpty(ServerResponse.noContent().build())
    }
}