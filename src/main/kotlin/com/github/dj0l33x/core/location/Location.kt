package com.github.dj0l33x.core.location

import com.github.dj0l33x.port.location.LocationEntity
import com.github.dj0l33x.port.location.LocationRepository
import java.time.ZoneOffset.UTC
import java.time.ZonedDateTime

class Location private constructor(
    val id: Long? = null,
    val path: String? = null,
    val isActive: Boolean? = true,
    val createdAt: ZonedDateTime = ZonedDateTime.now(UTC),
    val updatedAt: ZonedDateTime? = null,
    val deletedAt: ZonedDateTime? = null,
    private val repository: LocationRepository,
) {

    companion object {
        var id: Long? = null
        var path: String? = null
        var isActive: Boolean? = null
        private lateinit var repository: LocationRepository

        fun withId(id: Long) = apply { this.id = id }
        fun withPath(path: String) = apply { this.path = path }
        fun withIsActive(isActive: Boolean) = apply { this.isActive = isActive }
        fun withRepository(repository: LocationRepository) = apply { this.repository = repository }

        fun build(): Location {
            val location = Location(id = id, path = path, isActive = isActive, repository = repository)
            this.id = null
            this.path = null
            this.isActive = null
            return location
        }
    }


    fun save(): Location {
        path ?: throw LocationRequiredParameterMissingException()
        isActive ?: throw LocationRequiredParameterMissingException()

        if (repository.get(path) != null) throw LocationAlreadyExistsException()
        return toLocation(repository.persist(LocationEntity(path = path, isActive = isActive, createdAt = createdAt)))
    }


    fun update(): Location {
        id ?: throw LocationRequiredParameterMissingException()
        path ?: throw LocationRequiredParameterMissingException()
        isActive ?: throw LocationRequiredParameterMissingException()

        val actualLocation = repository.get(id) ?: throw LocationNotFoundException()

        val anotherExistingLocation = repository.get(path)
        if (anotherExistingLocation != null && anotherExistingLocation.id != id) {
            throw LocationAlreadyExistsException()
        }

        actualLocation.path = path
        actualLocation.isActive = isActive
        return toLocation(repository.update(actualLocation))
    }


    fun delete(): Location {
        id ?: throw LocationRequiredParameterMissingException()
        return repository.delete(id)
            ?.let { toLocation(it) }
            ?: throw LocationNotFoundException()
    }


    fun getById(): Location? {
        id ?: throw LocationNotFoundException()
        return repository.get(id)?.let { toLocation(it) }
    }


    fun list(): List<Location> =
        repository.list().map { toLocation(it) }


    fun listAll(): List<Location> =
        repository.listAll().map { toLocation(it) }


    private fun toLocation(entity: LocationEntity) =
        Location(
            entity.id,
            entity.path,
            entity.isActive,
            entity.createdAt,
            entity.updatedAt,
            entity.deletedAt,
            repository
        )
}
