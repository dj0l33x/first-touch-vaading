package com.github.dj0l33x.core.location

import java.time.ZonedDateTime

data class LocationEntity(
    val id: Long,
    val data: String,
    val createdAt: ZonedDateTime,
)
