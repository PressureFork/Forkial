package com.forkial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.forkial.ui.ChatScreen // Assuming ChatScreen is in this package
import com.forkial.ui.ForkialTheme // Assuming ForkialTheme is in this package
import com.forkial.model.Message // Import Message if needed for sample data
import com.forkial.model.MessageType // Import MessageType if needed for sample data

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForkialTheme { // Apply the custom theme
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // For now, directly display ChatScreen.
                    // Later, this will be replaced by a navigation component.
                    ChatScreen(
                        // Sample messages for initial display, can be emptyList()
                        // initialMessages = sampleMessages, // Uncomment and define if you want sample messages
                        currentUserId = "sampleUser123" // Placeholder current user ID
                    )
                }
            }
        }
    }
}

// Example of sample messages (optional, can be removed if ChatScreen handles empty state well)
// val sampleMessages = listOf(
//     Message("1", "chat1", "otherUser", "Hello from MainActivity!", MessageType.TEXT, System.currentTimeMillis() - 200000),
//     Message("2", "chat1", "sampleUser123", "Hi there! This is a sample message.", MessageType.TEXT, System.currentTimeMillis() - 100000)
// )
