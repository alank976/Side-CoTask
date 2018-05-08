package alan.team.sidecotask.v2.`interface`

import java.time.LocalDate

data class TaskDto(
        val id: Int?,
        val date: LocalDate?,
        val personName: String?,
        val taskName: String?,
        val hourSpent: Double?
)