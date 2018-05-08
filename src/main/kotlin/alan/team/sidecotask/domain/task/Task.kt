package alan.team.sidecotask.domain.task

import java.time.LocalDate

data class Task(
        val id: Int?,
        val _when: LocalDate?,
        val who: String?,
        val what: String?,
        val howLong: Double?
)