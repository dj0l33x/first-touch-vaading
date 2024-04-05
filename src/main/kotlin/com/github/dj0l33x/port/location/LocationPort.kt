package com.github.dj0l33x.port.location

import org.springframework.stereotype.Service

interface LocationService {
    fun saveLocation(locationSave: LocationSave)
    fun deleteLocation(locationDelete: LocationDelete)
    fun getLocations(): List<LocationList>
}

@Service
private class LocationServiceImpl : LocationService {
    override fun saveLocation(locationSave: LocationSave) {
        TODO("Not yet implemented")
    }

    override fun deleteLocation(locationDelete: LocationDelete) {
        TODO("Not yet implemented")
    }

    override fun getLocations(): List<LocationList> {
        return listOf()
    }
}
