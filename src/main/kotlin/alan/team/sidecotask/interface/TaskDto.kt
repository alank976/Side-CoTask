package alan.team.sidecotask.v2.`interface`

import java.time.LocalDate

data class TaskDto(
        val id: Int?,
        val _when: LocalDate?,
        val who: String?,
        val what: String?,
        val howLong: Double?
)