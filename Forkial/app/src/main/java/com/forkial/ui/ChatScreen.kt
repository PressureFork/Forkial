package com.forkial.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.forkial.model.Message
import com.forkial.model.MessageType
import com.forkial.ui.ForkialTheme // Corrected import path from previous steps
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    // For a real app, messages and currentUserId would come from a ViewModel or parent Composable
    initialMessages: List<Message> = emptyList(),
    currentUserId: String,
    // onSendMessage: (String) -> Unit // This will be handled internally for now by adding to a mutable list
) {
    // Use mutableStateListOf to allow messages to be added dynamically
    val messages = remember { mutableStateListOf<Message>().apply { addAll(initialMessages) } }
    val listState = rememberLazyListState()

    // Scroll to the bottom when a new message is added
    // Note: The list is reversed in LazyColumn, so index 0 is the newest item at the bottom.
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(0)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat with UserX") }, // Replace with actual chat title
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
                // TODO: Add navigation icon, actions for call, info, etc.
            )
        },
        bottomBar = {
            ChatInputBar(onSendMessage = { text ->
                val newMessage = Message(
                    id = UUID.randomUUID().toString(),
                    conversationId = "chat1", // Hardcoded for now
                    senderId = currentUserId,
                    content = text,
                    type = MessageType.TEXT,
                    timestamp = System.currentTimeMillis()
                )
                messages.add(newMessage) // Add to the end of the list
            })
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Apply padding from Scaffold
                .padding(horizontal = 8.dp), // Additional padding for messages
            reverseLayout = true // New messages appear at the bottom
        ) {
            // items are displayed from the original list but laid out from bottom to top.
            // To have newest messages (added at the end of `messages` list) at the bottom,
            // we iterate through `messages` normally and `reverseLayout = true` handles the visual order.
            // If we were to add new messages to the start of the list, then .reversed() would be needed
            // or reverseLayout = false and add to index 0.
            // Current setup: add to end, reverseLayout=true, iterate normal.
            // For items to be correctly keyed and animations to work well, especially with .reversed(),
            // it's good practice to provide a key in items { message -> ... }
            items(
                items = messages, // Iterate through the list normally
                key = { message -> message.id } // Provide a stable key
            ) { message ->
                MessageBubble(
                    message = message,
                    isFromCurrentUser = message.senderId == currentUserId
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Chat Screen Preview (Dark)")
@Composable
fun ChatScreenPreviewDark() {
    val sampleMessages = remember { // Use remember for previews too if they involve stateful composables
        listOf(
            Message("1", "chat1", "otherUser", "Hey! How are you?", MessageType.TEXT, System.currentTimeMillis() - 100000),
            Message("2", "chat1", "currentUser", "I'm good, thanks! How about you?", MessageType.TEXT, System.currentTimeMillis() - 50000),
            Message("3", "chat1", "otherUser", "Doing great! Just working on this cool app.", MessageType.TEXT, System.currentTimeMillis())
        )
    }
    ForkialTheme(darkTheme = true) {
        ChatScreen(
            initialMessages = sampleMessages,
            currentUserId = "currentUser"
        )
    }
}

@Preview(showBackground = true, name = "Chat Screen Preview (Light)")
@Composable
fun ChatScreenPreviewLight() {
    val sampleMessages = remember {
        listOf(
            Message("1", "chat1", "otherUser", "Hey! How are you?", MessageType.TEXT, System.currentTimeMillis() - 100000),
            Message("2", "chat1", "currentUser", "I'm good, thanks! How about you?", MessageType.TEXT, System.currentTimeMillis() - 50000),
            Message("3", "chat1", "otherUser", "Doing great! Just working on this cool app.", MessageType.TEXT, System.currentTimeMillis())
        )
    }
    ForkialTheme(darkTheme = false) { // Test light theme
        ChatScreen(
            initialMessages = sampleMessages,
            currentUserId = "currentUser"
        )
    }
}

@Preview(showBackground = true, name = "Chat Screen Empty Preview (Dark)")
@Composable
fun ChatScreenEmptyPreviewDark() {
    ForkialTheme(darkTheme = true) {
        ChatScreen(
            initialMessages = emptyList(),
            currentUserId = "currentUser"
        )
    }
}
