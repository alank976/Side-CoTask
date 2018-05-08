package alan.team.sidecotask.domain.task

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface TaskRepository : ReactiveMongoRepository<Task, Int>

