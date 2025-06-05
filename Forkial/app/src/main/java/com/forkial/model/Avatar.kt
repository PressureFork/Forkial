package com.forkial.model

data class AvatarCustomization(
    val baseModelId: String = "default_human",
    val facialStructureId: String = "oval",
    val hairStyleId: String = "short_black",
    val hairColor: String = "#000000", // Hex color
    val eyeColor: String = "#553311", // Hex color
    val skinTone: String = "#C68642", // Hex color
    val bodyTypeId: String = "medium",
    val clothingTopId: String? = null,
    val clothingBottomId: String? = null,
    val clothingOutfitId: String? = null, // For full outfits
    val shoesId: String? = null,
    val accessories: List<String> = emptyList(), // e.g., ["glasses_style_1", "hat_style_2"]
    val emotesPackId: String = "default_emotes"
)

data class Avatar(
    val id: String, // Unique Avatar ID, could be same as UserID or separate
    val userId: String, // Owning user
    val customization: AvatarCustomization,
    val currentAnimation: String = "idle", // e.g., "idle", "walking", "waving"
    val equippedItems: List<String> = emptyList() // Special items or temporary attachments
)
