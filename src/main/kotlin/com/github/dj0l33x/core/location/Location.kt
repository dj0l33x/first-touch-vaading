package com.github.dj0l33x.core.location

import java.time.ZoneOffset.UTC
import java.time.ZonedDateTime
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

class Location(val id: Long, val data: String, val createdAt: ZonedDateTime) {

    companion object {
        private val ids = AtomicLong(0)
        private val db = ConcurrentHashMap<Long, LocationEntity>()

        fun save(data: String): Location {
            val id = ids.incrementAndGet()
            val entity = LocationEntity(id, data, ZonedDateTime.now(UTC))
            db.put(id, entity)
            return Location(entity.id, entity.data, entity.createdAt)
        }

        fun update(id: Long, data: String): Location? {
            val newEntity = db.get(id)?.let { LocationEntity(it.id, data, it.createdAt) }
            if (newEntity == null) {
                throw LocationNotFoundException()
            }
            db.put(id, newEntity)
            return Location(newEntity.id, newEntity.data, newEntity.createdAt)
        }

        fun delete(id: Long): Location? =
            db.remove(id)?.let { Location(it.id, it.data, it.createdAt) }

        fun list(): List<Location> =
            db.values.map { Location(it.id, it.data, it.createdAt) }
    }

}
