package com.example.accessibilitybug

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalTextApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LinkClickableText(annotatedString = buildAnnotatedString {
                append("Not a link, but ")
                pushUrlAnnotation(UrlAnnotation(url = "https://www.google.com"))
                withStyle(style = SpanStyle(Color.Blue)) {
                    append("this is a link that when clicked should open Google.com.")
                }
                pop()
                append(" And here's another string without a link.")
            })
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
private fun LinkClickableText(
    annotatedString: AnnotatedString
) {
    val uriHandler = LocalUriHandler.current
    ClickableText(
        modifier = Modifier.padding(8.dp),
        text = annotatedString,
        style = MaterialTheme.typography.body1,
        onClick = { offset ->
            Log.i("ClickableText", "OnClick called.")
            annotatedString.getUrlAnnotations(
                start = offset,
                end = offset
            ).firstOrNull()?.let { annotation ->
                uriHandler.openUri(annotation.item.url)
                Log.i("ClickableText", "Url opened.")
            }
        }
    )
}