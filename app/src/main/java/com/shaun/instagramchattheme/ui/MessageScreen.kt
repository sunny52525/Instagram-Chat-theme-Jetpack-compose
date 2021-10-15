package com.shaun.instagramchattheme.ui

import android.animation.ArgbEvaluator
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shaun.instagramchattheme.data.Constants
import com.shaun.instagramchattheme.data.MessageType
import com.shaun.instagramchattheme.ui.theme.DarkestPurple
import com.shaun.instagramchattheme.ui.theme.LightestBlue

@Composable
@Preview
fun MessageScreen() {

    val screenHeight = LocalConfiguration.current.screenHeightDp.toFloat()


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {
        itemsIndexed(Constants.messagesMerged) { index, message ->


            if (message.type == MessageType.SENT) {
                var backgroundColor by remember { mutableStateOf(LightestBlue) }

                Column(
                    Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates: LayoutCoordinates ->
                            if (screenHeight > 0f) {
                                val topOffset =
                                    coordinates.boundsInParent().top.getOffset(screenHeight)

                                backgroundColor = Color(
                                    ArgbEvaluator()
                                        .evaluate(
                                            topOffset / screenHeight,
                                            DarkestPurple.toArgb(),
                                            LightestBlue.toArgb()
                                        )
                                        .toString()
                                        .toInt()
                                )
                            }
                        }
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth(0.7f)
                            .align(Alignment.End)
                    ) {
                        SentMessage(
                            message = message.message,
                            backgroundColor = backgroundColor,
                            messagePosition = message.messagePosition,
                            modifier = Modifier
                                .align(Alignment.End)

                        )
                        if (index != 0) {
                            if (Constants.messagesMerged[index - 1].type == MessageType.RECEIVED)
                                Spacer(modifier = Modifier.height(10.dp))
                            else
                                Spacer(modifier = Modifier.height(5.dp))
                        } else
                            Spacer(modifier = Modifier.height(5.dp))
                    }
                }
            } else {

                Column(Modifier.fillMaxWidth()) {
                    Column(
                        Modifier
                            .fillMaxWidth(0.7f)
                            .align(Alignment.Start)
                    ) {
                        ReceivedMessage(
                            message = message.message,
                            messagePosition = message.messagePosition,
                            modifier = Modifier.align(Alignment.Start)

                        )
                        if (index != 0) {
                            if (Constants.messagesMerged[index - 1].type == MessageType.SENT)
                                Spacer(modifier = Modifier.height(10.dp))
                            else
                                Spacer(modifier = Modifier.height(5.dp))
                        } else
                            Spacer(modifier = Modifier.height(5.dp))
                    }
                }

            }
        }
    }

}

fun Float.getOffset(screenHeight: Float): Float {

    if (this < 0) return 0f
    if (this > screenHeight) return screenHeight
    return this
}

