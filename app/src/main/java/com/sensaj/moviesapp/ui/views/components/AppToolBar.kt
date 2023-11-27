package com.sensaj.moviesapp.ui.views.components

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolBar(
    title:String = "",
    onMenuClick: ()->Unit,
    onSearchClick: (startSearch:Boolean)->Unit
) {
    var startSearch by remember {
        mutableStateOf(false)
    }
    TopAppBar(
        navigationIcon = {
//            Icon(
//                imageVector = Icons.Filled.Menu,
//                contentDescription = null,
//                tint= Color.Black,
//                modifier = Modifier.clickable { onMenuClick() }
//            )
        },
        title = {
                Column {
                    Text(text = title)
                }
        },
        actions = {
            AppIconButton(
                icon =if (startSearch) Icons.Filled.Clear else  Icons.Filled.Search,
                onClick = {
                    startSearch = !startSearch
                    onSearchClick(startSearch)
                    },
                )
        }
    )
}