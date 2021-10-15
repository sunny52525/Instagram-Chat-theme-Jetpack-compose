package com.shaun.instagramchattheme.data

data class Message(
    val message: String,
    val type: MessageType,
    val messagePosition: MessagePosition,
)

enum class MessageType {
    SENT,
    RECEIVED
}

enum class MessagePosition {
    SINGLE,
    MIDDLE,
    TOP,
    BOTTOM
}