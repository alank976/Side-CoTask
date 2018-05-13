package alan.team.sidekotask.domain.task

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface TaskRepository : ReactiveMongoRepository<Task, String>

