package alan.team.sidecotask.domain.task

import java.time.LocalDate

data class Task(
        val id: Int?,
        val date: LocalDate?,
        val personName: String?,
        val taskName: String?,
        val hourSpent: Double?
)