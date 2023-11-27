package com.sensaj.moviesapp.ui.views.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppText(
    text:String,
    modifier: Modifier=Modifier,
    maxLines: Int = 2,
    onClick: (() -> Unit?)? = null
) {
    Text(
        text =  truncateText(text, maxLines),
        modifier = modifier.clickable {
              if (onClick!=null){
                  onClick.invoke()
              }
        },
    )
}
fun truncateText(text: String, maxLines: Int): String {
    val lines = text.lines()

    return if (lines.size > maxLines) {
        lines.take(maxLines).joinToString("\n") +
                "\n... (Tap to show more)"
    } else {
        text
    }
}