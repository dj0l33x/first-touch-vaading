package com.github.dj0l33x.port.location

import org.springframework.stereotype.Component
import java.time.ZoneOffset.UTC
import java.time.ZonedDateTime
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

interface LocationRepository {
    fun get(id: Long): LocationEntity?
    fun get(path: String): LocationEntity?
    fun delete(id: Long): LocationEntity?
    fun persist(entity: LocationEntity): LocationEntity
    fun update(entity: LocationEntity): LocationEntity
    fun list(): List<LocationEntity>
    fun listAll(): List<LocationEntity>
}

@Component
open class LocationRepositoryImpl : LocationRepository {

    private val ids = AtomicLong(0)
    private val db = ConcurrentHashMap<Long, LocationEntity>()

    override fun get(id: Long): LocationEntity? = db[id]

    override fun get(path: String): LocationEntity? = db.values.firstOrNull { it.path == path }

    override fun list(): List<LocationEntity> = db.values.filter { it.deletedAt == null }.toList()

    override fun listAll(): List<LocationEntity> = db.values.toList()

    override fun delete(id: Long): LocationEntity? {
        val entity = db[id] ?: return null
        entity.deletedAt = ZonedDateTime.now(UTC)
        db[id] = entity
        return entity
    }

    override fun persist(entity: LocationEntity): LocationEntity {
        val id = ids.incrementAndGet()
        entity.id = id
        db[id] = entity
        return entity
    }

    override fun update(entity: LocationEntity): LocationEntity {
        db[entity.id!!] = entity
        entity.updatedAt = ZonedDateTime.now(UTC)
        return entity
    }
}
