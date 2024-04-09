package com.github.dj0l33x.port.location

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class CreateLocation(
    @NotBlank @NotNull val path: String,
    @NotBlank @NotNull val isActive: Boolean
)

data class UpdateLocation(
    @NotBlank @NotNull val id: Long,
    @NotBlank @NotNull val path: String,
    @NotBlank @NotNull val isActive: Boolean
)

data class DeleteLocation(
    @NotBlank @NotNull val id: Long,
)

data class GetLocation(
    val id: Long,
    val path: String,
    val isActive: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?,
    val deletedAt: LocalDateTime?,
)
