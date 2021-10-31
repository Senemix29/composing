package com.natanximenes.composing.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.natanximenes.composing.ui.model.Message
import com.natanximenes.composing.ui.theme.ComposingTheme
import com.natanximenes.composing.ui.view.Conversation
import com.natanximenes.composing.ui.view.MessageCard
import com.natanximenes.composing.util.SampleData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposingTheme {
                Conversation(messages = SampleData().conversationSample)
            }
        }
    }
}
