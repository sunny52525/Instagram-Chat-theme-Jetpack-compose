package com.shaun.instagramchattheme.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shaun.instagramchattheme.R
import com.shaun.instagramchattheme.data.MessagePosition
import com.shaun.instagramchattheme.ui.theme.LightBlack
import com.shaun.instagramchattheme.ui.theme.LightestGray
import com.shaun.instagramchattheme.ui.theme.SkyBlue

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


@Composable
fun MessageBox(
    modifier: Modifier=Modifier,
    message: String,
    onChange: (String) -> Unit,
    onSend: () -> Unit
) {

    Card(shape = CircleShape, modifier = modifier.fillMaxWidth(), backgroundColor = LightBlack) {

        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Card(shape = CircleShape, backgroundColor = SkyBlue) {
                Image(
                    painter = painterResource(id = R.drawable.camera),
                    contentDescription = "",
                    modifier = Modifier.padding(4.dp),

                    )
            }

            TextField(
                value = message,
                onValueChange = onChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Message...", color = LightestGray)
                },
                trailingIcon = {
                    Row(Modifier.padding(horizontal = 5.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.mic),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.image),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(Color.White)

                        )
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = Color.Transparent,
                    backgroundColor = LightBlack,
                    cursorColor = Color.White,
                    focusedLabelColor = Color.Transparent,
                    textColor = Color.White
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        onSend()
                    }
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send)
            )

        }

    }

}

@Preview
@Composable
fun AppTopBar(modifier: Modifier= Modifier) {
    TopAppBar(
        backgroundColor = Color.Black,
        elevation = 40.dp,
        modifier = modifier
    ) {

        Row(
            Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(10.dp))
                Card(shape = CircleShape) {
                    Image(
                        painter = painterResource(id = R.drawable.lanajpg),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )

                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Lana del ray", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Row {
                Image(
                    painter = painterResource(id = R.drawable.phone),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.size(30.dp)


                )
                Spacer(modifier = Modifier.width(20.dp))

                Image(
                    painter = painterResource(id = R.drawable.videocam),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.size(30.dp)

                )
            }

        }
    }
}


@Preview
@Composable
fun MessageBoxPreview() {

    MessageBox(message = "Hello moto", onChange = { /*TODO*/ }) {


    }
}
