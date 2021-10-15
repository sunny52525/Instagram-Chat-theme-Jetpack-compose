package com.shaun.instagramchattheme.data

object Constants {
    val messages = listOf(
        Message(
            message = "strict parent(kinda)?",
            type = MessageType.SENT,
            messagePosition = MessagePosition.SINGLE
        ),
        Message(
            message = "O yes",
            type = MessageType.RECEIVED,
            messagePosition = MessagePosition.SINGLE
        ),
        Message(
            message = "Hey",

            type = MessageType.SENT,
            messagePosition = MessagePosition.TOP,
        ),
        Message(
            message = "Suno",
            type = MessageType.SENT,
            messagePosition = MessagePosition.MIDDLE
        ),
        Message(
            message = "How are you now",
            type = MessageType.SENT,
            messagePosition = MessagePosition.BOTTOM
        ),
        Message(
            message = "Sahi hai",
            type = MessageType.RECEIVED,
            messagePosition = MessagePosition.SINGLE
        ),
        Message(
            message = "Good",
            type = MessageType.SENT,
            messagePosition = MessagePosition.TOP
        ),
        Message(
            message = "Nice.",
            type = MessageType.SENT, messagePosition = MessagePosition.BOTTOM
        ),
        Message(
            message = "How about you?",
            type = MessageType.RECEIVED,
            messagePosition = MessagePosition.TOP
        ),
        Message(
            message = "Glad to meet you",
            type = MessageType.RECEIVED,
            messagePosition = MessagePosition.MIDDLE
        ),
        Message(
            message = "We should meet",
            type = MessageType.RECEIVED, messagePosition = MessagePosition.BOTTOM
        ),
        Message(
            message = "Definitely",
            type = MessageType.SENT, MessagePosition.SINGLE
        ),
        Message(
            message = "Yeah don't worry",
            type = MessageType.SENT, MessagePosition.TOP
        ),
        Message(
            message = "Tommorow 9 pm ",
            type = MessageType.SENT, MessagePosition.BOTTOM
        ),
        Message(
            message = "Yeaaah",
            type = MessageType.SENT,
            MessagePosition.SINGLE
        )
    )
    val messagesMerged = messages + messages + messages + messages


}