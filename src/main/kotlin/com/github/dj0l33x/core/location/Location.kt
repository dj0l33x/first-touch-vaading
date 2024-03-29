package com.github.dj0l33x.core.location

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.ZoneOffset
import java.time.ZonedDateTime

@Entity
data class Location(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var value: String = "",

    @Column(nullable = false)
    var createdAt: ZonedDateTime = ZonedDateTime.now(ZoneOffset.UTC)
)

