package com.github.dj0l33x.port.location

import com.github.dj0l33x.core.location.Location
import org.springframework.stereotype.Service


interface LocationService {
    fun saveLocation(createLocation: CreateLocation): GetLocation
    fun updateLocation(updateLocation: UpdateLocation): GetLocation
    fun deleteLocation(deleteLocation: DeleteLocation): GetLocation
    fun loadLocations(): List<GetLocation>
    fun loadAllLocations(): List<GetLocation>
}


@Service
class LocationServiceImpl(repository: LocationRepository) : LocationService {

    init {
        Location.withRepository(repository)
        Location.withPath("/mnt/ssd").withIsActive(true).build().save()
        Location.withPath("/mnt/hdd").withIsActive(true).build().save()
        Location.withPath("/mnt/mss").withIsActive(false).build().save()
        Location.withPath("/opt/prom").withIsActive(false).build().save()

        Location.withId(1).withPath("/mnt/ssd").withIsActive(false).build().update()
        Location.withId(4).withPath("/opt/prom1").withIsActive(true).build().update()
        Location.withId(3).build().delete()
    }


    override fun saveLocation(create: CreateLocation): GetLocation =
        Location.withPath(create.path).withIsActive(create.isActive).build()
            .save()
            .let { toGetLocation(it) }


    override fun updateLocation(update: UpdateLocation): GetLocation =
        Location.withId(update.id).withPath(update.path).withIsActive(update.isActive)
            .build()
            .update()
            .let { toGetLocation(it) }


    override fun deleteLocation(deleteLocation: DeleteLocation): GetLocation =
        Location.withId(deleteLocation.id).build()
            .delete()
            .let { toGetLocation(it) }


    override fun loadLocations(): List<GetLocation> =
        Location.build()
            .list()
            .map { toGetLocation(it) }


    override fun loadAllLocations(): List<GetLocation> =
        Location.build()
            .listAll()
            .map { toGetLocation(it) }


    private fun toGetLocation(location: Location): GetLocation =
        GetLocation(
            location.id!!,
            location.path!!,
            location.isActive!!,
            location.createdAt.toLocalDateTime(),
            location.updatedAt?.toLocalDateTime(),
            location.deletedAt?.toLocalDateTime(),
        )
}
