package com.github.dj0l33x.port.location

import org.springframework.stereotype.Service
import java.time.ZoneOffset
import java.time.ZonedDateTime

@Service
class LocationService {

    fun getLocations(): List<LocationDto> {
        return listOf(
            LocationDto(1L, "Test-1", ZonedDateTime.now(ZoneOffset.UTC).minusDays(2)),
            LocationDto(2L, "Test-2", ZonedDateTime.now(ZoneOffset.UTC).minusDays(1)),
            LocationDto(3L, "Test-3", ZonedDateTime.now(ZoneOffset.UTC)),
        )
    }
}
