package com.shaun.instagramchattheme.ui

import android.animation.ArgbEvaluator
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.zIndex
import com.shaun.instagramchattheme.data.Constants
import com.shaun.instagramchattheme.data.Message
import com.shaun.instagramchattheme.data.MessagePosition
import com.shaun.instagramchattheme.data.MessageType
import com.shaun.instagramchattheme.ui.theme.DarkestPurple
import com.shaun.instagramchattheme.ui.theme.LightestBlue
import kotlinx.coroutines.launch

@Composable
@Preview
fun MessageScreen() {

    val scrollState = rememberLazyListState()
    var message by remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()

    var messages: ArrayList<Message> by remember {
        mutableStateOf(Constants.messagesMerged)
    }
    val screenHeight = LocalConfiguration.current.screenHeightDp.toFloat()
    Box(Modifier.fillMaxSize()) {

        AppTopBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .zIndex(50f)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(horizontal = 16.dp),
            state = scrollState,
        ) {
            itemsIndexed(messages) { index, message ->


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
                                if (messages[index - 1].type == MessageType.RECEIVED)
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
                                if (messages[index - 1].type == MessageType.SENT)
                                    Spacer(modifier = Modifier.height(10.dp))
                                else
                                    Spacer(modifier = Modifier.height(5.dp))
                            } else
                                Spacer(modifier = Modifier.height(5.dp))
                        }
                    }

                }
            }

            item {
                Spacer(modifier = Modifier.height(70.dp))
            }
        }

        MessageBox(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(65.dp),
            message = message, onChange = {
                message = it
            }) {

            val mess = messages
            messages = arrayListOf()

            messages = mess.also {
                it.add(
                    Message(
                        message = message,
                        messagePosition = MessagePosition.TOP,
                        type = MessageType.SENT
                    )
                )
            }


            scope.launch {

                scrollState.scrollToItem(index = messages.lastIndex - 1, 0)
            }

            message = ""
        }

    }
    LaunchedEffect(key1 = messages, block = {
        scrollState.scrollToItem(index = messages.lastIndex - 1, 0)

    })

}

fun Float.getOffset(screenHeight: Float): Float {

    if (this < 0) return 0f
    if (this > screenHeight) return screenHeight
    return this
}

