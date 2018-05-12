package alan.team.sidecotask

import alan.team.sidecotask.domain.task.Task
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.ParameterizedTypeReference
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureWebTestClient
class TaskIT {
    @Autowired
    private lateinit var client: WebTestClient

    @Test
    fun `test task CRUD`() {
        `then GET should return no task`()

        val first = Task(null, personName = "A", taskName = "B", hourSpent = 2.0)
        `when save task`(first)
        `then GET should return tasks`(listOf(first))
        val firstTaskId = getTaskId(0)!!

        val second = Task(null, personName = "A2", taskName = "B2", hourSpent = .5)
        `when save task`(second)
        `then GET should return tasks`(listOf(first, second))
        `then GET a task should equal to expected`(firstTaskId, first)

        `when delete task`(firstTaskId)
        `then GET should return tasks`(listOf(second))
        `then GET a task should not be found`(firstTaskId)
    }

    private fun `when delete task`(id: String) {
        client
                .delete()
                .uri("/api/v1/tasks/$id")
                .exchange()
                .expectStatus().isOk
    }

    private fun `then GET a task should not be found`(id: String) {
        client
                .get()
                .uri("/api/v1/tasks/$id")
                .exchange()
                .expectStatus().isNotFound
    }

    private fun `then GET a task should equal to expected`(id: String, expected: Task) {
        client
                .get()
                .uri("/api/v1/tasks/$id")
                .exchange()
                .expectStatus().isOk
                .expectBody(Task::class.java)
                .returnResult()
                .apply {
                    assertThat(responseBody)
                            .isEqualToIgnoringGivenFields(expected, "id")
                }
    }

    private fun `when save task`(task: Task) {
        client
                .post()
                .uri("/api/v1/tasks")
                .syncBody(task)
                .exchange()
                .expectStatus().isCreated
                .expectBody(Task::class.java)
                .returnResult()
                .apply {
                    assertThat(responseBody)
                            .isEqualToIgnoringGivenFields(task, "id")
                }
    }

    private fun `then GET should return no task`() {
        `then GET should return tasks`(emptyList())
    }

    private fun getTaskId(element: Int): String? {
        val type = object : ParameterizedTypeReference<List<Task>>() {}
        return client
                .get()
                .uri("/api/v1/tasks")
                .exchange()
                .expectStatus().isOk
                .expectBody(type)
                .returnResult()
                .responseBody!![element]
                .id
    }

    private fun `then GET should return tasks`(tasks: List<Task>) {
        val type = object : ParameterizedTypeReference<List<Task>>() {}
        client
                .get()
                .uri("/api/v1/tasks")
                .exchange()
                .expectStatus().isOk
                .expectBody(type)
                .returnResult()
                .apply {
                    assertThat(responseBody)
                            .usingElementComparatorIgnoringFields("id")
                            .isEqualTo(tasks)
                }
    }
}
