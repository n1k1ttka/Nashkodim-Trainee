package com.example.nashkodimtrainee.core.domain.model

sealed class ResourceState {
    object LOADING : ResourceState()
    object MESSAGE : ResourceState()
    object SUCCESS : ResourceState()
    object ERROR : ResourceState()
    object NETWORK_ERROR : ResourceState()
}