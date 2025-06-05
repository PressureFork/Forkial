package com.forkial.model

data class RoomItem(
    val itemId: String, // ID of the item model/asset
    val positionX: Float,
    val positionY: Float,
    val positionZ: Float,
    val rotation: Float,
    val scale: Float = 1.0f,
    val ownerId: String? = null // If the item is owned/placed by a user
)

data class RoomParticipant(
    val userId: String,
    val avatarId: String,
    val displayName: String,
    val positionX: Float,
    val positionY: Float,
    // val positionZ: Float, // For 2.5D, Z might be implicit or fixed
    val currentAnimation: String = "idle"
)

data class RoomState(
    val id: String, // Unique Room ID
    val name: String,
    val description: String? = null,
    val themeId: String, // e.g., "arcade", "rooftop_cafe"
    val maxParticipants: Int = 20,
    val participants: Map<String, RoomParticipant> = emptyMap(), // UserID to RoomParticipant
    val items: List<RoomItem> = emptyList(), // Furniture, decor, etc.
    val ownerId: String? = null, // If it's a user-created room
    val isPublic: Boolean = true,
    val musicTrackUrl: String? = null
)
