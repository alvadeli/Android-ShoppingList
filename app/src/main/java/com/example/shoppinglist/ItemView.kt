package com.example.shoppinglist

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp


@Composable
fun AnnotatedStringPreview() {
    val annotatedString = createAnnotatedString("This is a strikethrough")

    Text(
        text = annotatedString,
        modifier = Modifier.padding(16.dp)
    )
}

@Preview
@Composable
fun AnnotatedStringPreviewDemo() {
    AnnotatedStringPreview()
}

fun createAnnotatedString(text: String): AnnotatedString {
    return buildAnnotatedString {
        withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) {
            append(text)
        }
    }
}