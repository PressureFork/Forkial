package com.forkial.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.forkial.model.Message
import com.forkial.model.MessageType
// import com.forkial.model.User // User model not directly used in this version of MessageBubble
import com.forkial.ui.ForkialTheme // Corrected import path

@Composable
fun MessageBubble(message: Message, isFromCurrentUser: Boolean) {
    val bubbleColor = if (isFromCurrentUser) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer
    val textColor = if (isFromCurrentUser) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSecondaryContainer
    val alignment = if (isFromCurrentUser) Alignment.CenterEnd else Alignment.CenterStart

    // Define different corner radii for a more "chat bubble" look
    val bubbleShape = if (isFromCurrentUser) {
        RoundedCornerShape(topStart = 16.dp, topEnd = 4.dp, bottomStart = 16.dp, bottomEnd = 16.dp)
    } else {
        RoundedCornerShape(topStart = 4.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 16.dp)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = alignment
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f) // Max width for a bubble
                .clip(bubbleShape)
                .background(bubbleColor)
                .padding(12.dp),
            horizontalAlignment = if (isFromCurrentUser) Alignment.End else Alignment.Start
        ) {
            // TODO: Display sender name if not from current user and in a group chat
            // if (!isFromCurrentUser) {
            //     Text(
            //         text = message.senderId, // Replace with actual display name
            //         style = MaterialTheme.typography.labelSmall,
            //         color = MaterialTheme.colorScheme.onSurfaceVariant,
            //         modifier = Modifier.padding(bottom = 4.dp)
            //     )
            // }
            Text(
                text = message.content,
                style = MaterialTheme.typography.bodyLarge,
                color = textColor
            )
            // TODO: Add timestamp
            // Text(
            //     text = java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault()).format(java.util.Date(message.timestamp)),
            //     style = MaterialTheme.typography.labelSmall,
            //     color = textColor.copy(alpha = 0.7f),
            //     modifier = Modifier.padding(top = 4.dp)
            // )
        }
    }
}

@Preview(showBackground = true, name = "Sent Message Preview")
@Composable
fun SentMessageBubblePreview() {
    ForkialTheme(darkTheme = true) {
        MessageBubble(
            message = Message(
                id = "1",
                conversationId = "chat1",
                senderId = "currentUser",
                content = "Hey there! This is an example of a sent message. How does it look?",
                type = MessageType.TEXT,
                timestamp = System.currentTimeMillis()
            ),
            isFromCurrentUser = true
        )
    }
}

@Preview(showBackground = true, name = "Received Message Preview")
@Composable
fun ReceivedMessageBubblePreview() {
    ForkialTheme(darkTheme = true) {
        MessageBubble(
            message = Message(
                id = "2",
                conversationId = "chat1",
                senderId = "otherUser",
                content = "Hi! This is a received message. It looks pretty good!",
                type = MessageType.TEXT,
                timestamp = System.currentTimeMillis()
            ),
            isFromCurrentUser = false
        )
    }
}
