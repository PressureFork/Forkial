package com.forkial.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.forkial.model.Message
import com.forkial.model.MessageType
import com.forkial.ui.ForkialTheme // Corrected import path

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    // In a real app, this would come from a ViewModel
    messages: List<Message>,
    currentUserId: String,
    onSendMessage: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat with UserX") }, // Replace with actual chat title
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant, // Or primary for more emphasis
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
                // TODO: Add navigation icon, actions for call, info, etc.
            )
        },
        bottomBar = {
            ChatInputBar(onSendMessage = onSendMessage)
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Apply padding from Scaffold
                .padding(horizontal = 8.dp), // Additional padding for messages
            reverseLayout = true // New messages appear at the bottom
        ) {
            // Make sure the list is not empty before trying to reverse it or access items.
            if (messages.isNotEmpty()) {
                items(messages.reversed()) { message -> // Display newest messages first
                    MessageBubble(
                        message = message,
                        isFromCurrentUser = message.senderId == currentUserId
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Chat Screen Preview (Dark)")
@Composable
fun ChatScreenPreviewDark() {
    val sampleMessages = remember {
        listOf(
            Message("1", "chat1", "otherUser", "Hey! How are you?", MessageType.TEXT, System.currentTimeMillis() - 100000),
            Message("2", "chat1", "currentUser", "I'm good, thanks! How about you?", MessageType.TEXT, System.currentTimeMillis() - 50000),
            Message("3", "chat1", "otherUser", "Doing great! Just working on this cool app.", MessageType.TEXT, System.currentTimeMillis())
        )
    }
    ForkialTheme(darkTheme = true) {
        ChatScreen(
            messages = sampleMessages,
            currentUserId = "currentUser",
            onSendMessage = {}
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
            messages = sampleMessages,
            currentUserId = "currentUser",
            onSendMessage = {}
        )
    }
}

@Preview(showBackground = true, name = "Chat Screen Empty Preview (Dark)")
@Composable
fun ChatScreenEmptyPreviewDark() {
    ForkialTheme(darkTheme = true) {
        ChatScreen(
            messages = emptyList(),
            currentUserId = "currentUser",
            onSendMessage = {}
        )
    }
}
