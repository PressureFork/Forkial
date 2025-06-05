package com.forkial.webrtc

// import org.webrtc.PeerConnection // Will be used when WebRTC library is integrated

// Dummy PeerConnection.IceServer class for now, mirroring org.webrtc.PeerConnection.IceServer structure
// This would be removed once the actual WebRTC library is added.
object PeerConnection {
    data class IceServer private constructor(
        val uri: String,
        val uris: List<String>,
        val username: String,
        val password_DEPRECATED: String, // Corresponds to 'password' in builder
        val tlsCertPolicy: TlsCertPolicy,
        val hostname: String,
        val tlsAlpnProtocols: List<String>,
        val tlsEllipticCurves: List<String>
    ) {
        // Simplified builder for our dummy class
        class Builder(private val uris: List<String>) {
            private var username: String = ""
            private var password_DEPRECATED: String = "" // 'password'
            // Add other fields as needed for the dummy

            constructor(uri: String) : this(listOf(uri))

            fun setUsername(username: String): Builder = apply { this.username = username }
            fun setPassword(password: String): Builder = apply { this.password_DEPRECATED = password }
            // Add other setters as needed for the dummy

            fun createIceServer(): IceServer = IceServer(
                uri = uris.firstOrNull() ?: "",
                uris = uris,
                username = username,
                password_DEPRECATED = password_DEPRECATED,
                tlsCertPolicy = TlsCertPolicy.TLS_CERT_POLICY_SECURE, // Dummy default
                hostname = "", // Dummy default
                tlsAlpnProtocols = emptyList(), // Dummy default
                tlsEllipticCurves = emptyList() // Dummy default
            )
        }
        enum class TlsCertPolicy { TLS_CERT_POLICY_SECURE, TLS_CERT_POLICY_INSECURE_NO_CHECK }
    }
}


data class ICEConfig(
    val urls: List<String>,
    val username: String? = null,
    val credential: String? = null // Often referred to as password for TURN servers
) {
    /**
     * Converts this ICEConfig to the WebRTC library's PeerConnection.IceServer format.
     * This implementation uses a dummy PeerConnection.IceServer class for now.
     * Replace with actual org.webrtc.PeerConnection.IceServer once the library is integrated.
     */
    fun toWebRtcIceServer(): PeerConnection.IceServer {
        val builder = if (urls.size == 1) {
            PeerConnection.IceServer.Builder(urls.first())
        } else {
            PeerConnection.IceServer.Builder(urls)
        }
        username?.let { builder.setUsername(it) }
        credential?.let { builder.setPassword(it) }
        return builder.createIceServer()
    }
}

object DefaultIceServers {
    val STUN_SERVERS = listOf(
        ICEConfig(listOf("stun:stun.l.google.com:19302")),
        ICEConfig(listOf("stun:stun1.l.google.com:19302")),
        ICEConfig(listOf("stun:stun2.l.google.com:19302")),
        ICEConfig(listOf("stun:stun3.l.google.com:19302")),
        ICEConfig(listOf("stun:stun4.l.google.com:19302")),
        // Add more public STUN servers if desired
        // e.g., "stun:global.stun.twilio.com:3478" (requires account for TURN)
    )

    // Example for a TURN server (requires actual credentials)
    // val TURN_SERVER_EXAMPLE = ICEConfig(
    //     urls = listOf(
    //         "turn:your.turn.server.com:3478?transport=udp",
    //         "turn:your.turn.server.com:3478?transport=tcp",
    //         "turns:your.turn.server.com:443?transport=tcp" // Example for TURN over TLS
    //     ),
    //     username = "your_username",
    //     credential = "your_password"
    // )
}
