package com.forkial.model

enum class UserOnlineStatus {
    ONLINE, OFFLINE, IDLE, DO_NOT_DISTURB, INVISIBLE // Ghost Mode
}

enum class UserMoodVibe { // Emo-style "Mood Rings"
    NEUTRAL, CHILL, HAPPY, SAD, EXCITED, BUSY, PARTY, GAMING, STUDYING, GHOST_MODE
}

data class User(
    val id: String, // Unique user ID (e.g., UUID)
    val username: String, // Unique, a-z, 0-9, _
    val displayName: String, // Full name or preferred display name
    val bio: String? = null,
    val avatarId: String, // Reference to Avatar object/configuration
    val onlineStatus: UserOnlineStatus = UserOnlineStatus.OFFLINE,
    val currentMood: UserMoodVibe = UserMoodVibe.NEUTRAL,
    val lastSeenTimestamp: Long? = null, // Epoch milliseconds
    val creationTimestamp: Long = System.currentTimeMillis(),
    val customStatusMessage: String? = null,
    val friendsList: List<String> = emptyList(), // List of friend UserIDs
    val blockedUsers: List<String> = emptyList() // List of blocked UserIDs
    // val currentRealmId: String? = null // If user is in a RealSpace room
    // val locationPrecision: String? = null // e.g., "exact", "fuzzy", "city" for map privacy
)
