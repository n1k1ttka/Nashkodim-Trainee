package com.example.nashkodimtrainee.core.domain.model

data class Resource<out T> constructor(
    val state: ResourceState,
    val data: T? = null,
    val msg: String? = null
)