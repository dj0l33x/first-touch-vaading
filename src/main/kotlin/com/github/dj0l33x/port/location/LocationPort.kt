package com.github.dj0l33x.port.location

import com.github.dj0l33x.core.location.Location
import org.springframework.stereotype.Service


interface LocationService {
    fun saveLocation(locationSave: LocationSave): LocationGet
    fun deleteLocation(locationDelete: LocationDelete): LocationGet?
    fun getLocations(): List<LocationGet>
}


@Service
private class LocationServiceImpl : LocationService {

    init {
        Location.save("/opt/prometheus/cache")
        Location.save("/mnt/ssd")
        Location.save("/home/pi")
        Location.save("/mnt/hdd")
    }

    override fun saveLocation(locationSave: LocationSave): LocationGet =
        Location.save(locationSave.data)
            .let { LocationGet(it.id, it.data, it.createdAt.toLocalDateTime()) }


    override fun deleteLocation(locationDelete: LocationDelete) =
        Location.delete(locationDelete.id)
            ?.let { LocationGet(it.id, it.data, it.createdAt.toLocalDateTime()) }

    override fun getLocations(): List<LocationGet> =
        Location.list()
            .map { LocationGet(it.id, it.data, it.createdAt.toLocalDateTime()) }

}
