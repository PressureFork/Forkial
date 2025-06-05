package com.forkial.model

// Consider adding imports for timestamp if using java.time or similar
// import java.time.Instant

enum class MessageStatus {
    SENDING, SENT, DELIVERED, READ, FAILED
}

enum class MessageType {
    TEXT, IMAGE, VIDEO, AUDIO, FILE, LOCATION, CONTACT, STICKER, GIF,
    SYSTEM_NOTIFICATION, // For group changes, etc.
    FORK_REQUEST, // Request to fork a conversation
    FORK_JOINED,  // Notification that a fork was joined
    AVATAR_EMOTE // For sending avatar reactions/emotes
}

data class Message(
    val id: String, // Unique message ID (e.g., UUID)
    val conversationId: String, // ID of the chat/group it belongs to
    val senderId: String, // User ID of the sender
    // val recipientId: String?, // User ID of the recipient (for DMs, null for group chats)
    val content: String, // Actual message content (text, URL to media, serialized object)
    val type: MessageType = MessageType.TEXT,
    val timestamp: Long = System.currentTimeMillis(), // Epoch milliseconds
    val status: MessageStatus = MessageStatus.SENDING,
    val reactions: Map<String, String> = emptyMap(), // Map of UserID to EmojiReaction
    val editedTimestamp: Long? = null, // Timestamp of last edit, if any
    val repliedToMessageId: String? = null, // ID of the message this is a reply to
    val isForwarded: Boolean = false,
    val mentionedUserIds: List<String> = emptyList() // List of UserIDs mentioned
    // val forkParentId: String? = null // If this message is in a forked conversation
)
