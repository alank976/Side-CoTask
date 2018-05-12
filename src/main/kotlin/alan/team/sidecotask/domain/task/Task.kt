package alan.team.sidecotask.domain.task

import org.springframework.data.annotation.Id
import java.time.LocalDate

data class Task(
        @Id val id: String?,
        val date: LocalDate = LocalDate.now(),
        val personName: String?,
        val taskName: String?,
        val hourSpent: Double?
)