package com.forkial

import android.app.Application

/**
 * Application class for Forkial.
 *
 * This class is a good place for global initializations
 * (e.g., dependency injection, logging, WebRTC setup).
 */
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // TODO: Add any application-wide initializations here.
        // For example:
        // - Initialize Dependency Injection (e.g., Koin, Hilt)
        // - Initialize Logging library
        // - Initialize WebRTC related components if needed globally
        // - Setup any other global services or configurations
    }
}
