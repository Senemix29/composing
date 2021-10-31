package com.natanximenes.composing.ui.view

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.natanximenes.composing.R
import com.natanximenes.composing.ui.model.Message
import com.natanximenes.composing.ui.theme.ComposingTheme
import com.natanximenes.composing.util.SampleData

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn(Modifier.background(color = MaterialTheme.colors.background)) {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Composable
fun MessageCard(message: Message) {
    Row {
        ProfileImage(R.drawable.ic_launcher_foreground)
        Spacer(modifier = Modifier.width(8.dp))
        var isExpanded by remember { mutableStateOf(false) }
        MessageBody(modifier = Modifier.clickable { isExpanded = !isExpanded }, message, isExpanded)
    }
}

@Composable
private fun MessageBody(modifier: Modifier, message: Message, isExpanded: Boolean) {
    val surfaceColor: Color by animateColorAsState(
        if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
    )
    Column(modifier = modifier) {
        Text(
            text = "name: ${message.author}",
            color = MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.subtitle2
        )
        Spacer(modifier = Modifier.width(8.dp))
        Surface(
            shape = MaterialTheme.shapes.medium,
            elevation = 1.dp,
            color = surfaceColor,
            modifier = Modifier.animateContentSize().padding(1.dp)
        ) {
            Text(
                text = "message: ${message.text}",
                Modifier.padding(all = 4.dp),
                style = MaterialTheme.typography.body1,
                maxLines = if (isExpanded) Int.MAX_VALUE else 1
            )
        }
    }
}

@Composable
private fun ProfileImage(@DrawableRes profilePic: Int) {
    Image(
        modifier = Modifier
            .size(40.dp)
            .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape),
        painter = painterResource(id = profilePic),
        contentDescription = "Contact profile picture"
    )
}


@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)

@Composable
fun DefaultPreview() {
    ComposingTheme {
        Conversation(messages = SampleData().conversationSample)
    }
}
