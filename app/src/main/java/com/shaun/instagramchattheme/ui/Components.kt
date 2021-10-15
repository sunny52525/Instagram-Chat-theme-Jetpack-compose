package com.shaun.instagramchattheme.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shaun.instagramchattheme.R
import com.shaun.instagramchattheme.data.MessagePosition
import com.shaun.instagramchattheme.ui.theme.LightBlack

@Composable
fun SentMessage(
    modifier: Modifier = Modifier,
    message: String,
    backgroundColor: Color,
    messagePosition: MessagePosition,
) {

    val shape = getShape(messagePosition)
    Card(
        shape = shape, modifier = modifier

    ) {
        Box(
            modifier = Modifier
                .background(backgroundColor)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {

            Text(text = message, color = Color.White)
        }

    }

}


@Composable
fun ReceivedMessage(
    modifier: Modifier = Modifier,

    message: String,
    messagePosition: MessagePosition,
) {
    val shape = getShapeOfReceived(messagePosition)

    Row(
        verticalAlignment = Alignment.CenterVertically

    ) {
        if (messagePosition == MessagePosition.SINGLE || messagePosition == MessagePosition.BOTTOM) {
            Card(shape = CircleShape) {
                Image(
                    painter = painterResource(id = R.drawable.lanajpg),
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )

            }
        } else {
            Spacer(modifier = Modifier.width(30.dp))
        }

        Spacer(modifier = Modifier.width(10.dp))
        Card(
            modifier = modifier,
            shape = shape
        ) {
            Row(
                modifier = Modifier
                    .background(LightBlack)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = message, color = Color.White)
            }

        }
    }

}


fun getShape(messagePosition: MessagePosition): Shape {
    return when (messagePosition) {
        MessagePosition.SINGLE -> RoundedCornerShape(15.dp)
        MessagePosition.MIDDLE -> RoundedCornerShape(
            topStart = 15.dp,
            topEnd = 6.dp,
            bottomEnd = 6.dp,
            bottomStart = 15.dp
        )
        MessagePosition.TOP -> RoundedCornerShape(
            topStart = 15.dp,
            topEnd = 15.dp,
            bottomEnd = 6.dp,
            bottomStart = 15.dp
        )
        MessagePosition.BOTTOM -> RoundedCornerShape(
            topStart = 15.dp,
            topEnd = 6.dp,
            bottomEnd = 15.dp,
            bottomStart = 15.dp
        )
    }
}

fun getShapeOfReceived(messagePosition: MessagePosition): Shape {
    return when (messagePosition) {
        MessagePosition.SINGLE -> RoundedCornerShape(15.dp)
        MessagePosition.MIDDLE -> RoundedCornerShape(
            topStart = 6.dp,
            topEnd = 15.dp,
            bottomEnd = 15.dp,
            bottomStart = 6.dp
        )
        MessagePosition.TOP -> RoundedCornerShape(
            topStart = 15.dp,
            topEnd = 15.dp,
            bottomEnd = 15.dp,
            bottomStart = 6.dp
        )
        MessagePosition.BOTTOM -> RoundedCornerShape(
            topStart = 6.dp,
            topEnd = 15.dp,
            bottomEnd = 15.dp,
            bottomStart = 15.dp
        )
    }
}

