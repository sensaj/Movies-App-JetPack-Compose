package com.sensaj.moviesapp.ui.views.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@Composable
fun SearchField(
    isVisible:Boolean = false,
    modifier: Modifier = Modifier,
    hint : String = "",
    onSearch : (String)-> Unit = {}
) {
    var text by remember{
        mutableStateOf("")
    }

    var isHintDisplayed by remember {
        mutableStateOf(hint!="")
    }

    val transition = updateTransition(isVisible, label = "visibility")
    val animatedAlpha by transition.animateFloat(
        label = "alpha"
    ) { visibility ->
        if (visibility) 1f else 0f
    }
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { -it },
            animationSpec = tween(500)
        ) + fadeIn(initialAlpha = 0.5f),
        exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut()
    ) {

        Box(
            modifier = modifier
                .shadow(5.dp, CircleShape)
                .padding(5.dp)
                .background(Color.White, CircleShape)
                .alpha(animatedAlpha)
        ){
            BasicTextField(
                value = text,
                onValueChange = {
                    text =it
                    onSearch(it)
                },
                maxLines = 1,
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray.copy(alpha = .1f), CircleShape)
                    .padding(horizontal = 20.dp, vertical = 12.dp)
                    .onFocusChanged {
                        isHintDisplayed = !it.isFocused && text.isEmpty()
                    }
            )
            if(isHintDisplayed){
                Text(
                    text=hint,
                    color= Color.LightGray,
                    modifier=Modifier
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                )
            }
        }

    }
}