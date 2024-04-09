package com.github.dj0l33x.port.location

import java.time.ZonedDateTime

data class LocationEntity(
    var id: Long? = null,
    var path: String,
    var isActive: Boolean,
    var createdAt: ZonedDateTime,
    var updatedAt: ZonedDateTime? = null,
    var deletedAt: ZonedDateTime? = null,
)
