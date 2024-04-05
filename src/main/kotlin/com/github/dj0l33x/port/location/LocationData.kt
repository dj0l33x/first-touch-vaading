package com.github.dj0l33x.port.location

import java.time.LocalDateTime

data class LocationSave(val data: String)
data class LocationUpdate(val id: Long, val data: String)
data class LocationDelete(val id: Long)
data class LocationGet(val id: Long, val data: String, val createdAt: LocalDateTime)
