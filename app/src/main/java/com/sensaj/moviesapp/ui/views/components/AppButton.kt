package com.sensaj.moviesapp.ui.views.components

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun AppIconButton(
    onClick:()->Unit,
    enabled:Boolean = true,
    icon: ImageVector,
    iconColor: Color = LocalContentColor.current,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = iconColor)
    }
}