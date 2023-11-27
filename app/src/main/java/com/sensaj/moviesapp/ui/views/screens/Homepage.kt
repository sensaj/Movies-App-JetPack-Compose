package com.sensaj.moviesapp.ui.views.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sensaj.moviesapp.navigation.Routes
import com.sensaj.moviesapp.ui.views.components.AppIconButton
import com.sensaj.moviesapp.ui.views.components.AppToolBar
import com.sensaj.moviesapp.ui.views.components.CharacterContainer
import com.sensaj.moviesapp.ui.views.components.MovieCover
import com.sensaj.moviesapp.ui.theme.DarkGreen
import com.sensaj.moviesapp.viewmodels.CharacterListViewModel
import com.sensaj.moviesapp.ui.views.components.SearchField

data class MenuItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    navigator:NavController,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp


    val characters by remember {viewModel.characters}
    val loadingError by remember {viewModel.loadingError}
    val isLoading by remember {viewModel.isLoading}
    var startSearch by remember{mutableStateOf(false)}
    var isSearching by remember{mutableStateOf(false)}

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    val items = listOf<MenuItem>(
        MenuItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unSelectedIcon = Icons.Default.Home,
            route = Routes.HOME_SCREEN
        ),
        MenuItem(
            title = "Characters",
            selectedIcon = Icons.Filled.Person,
            unSelectedIcon = Icons.Default.Person,
            route = Routes.CHARACTER_SCREEN
        ),
        MenuItem(
            title = "Spells",
            selectedIcon = Icons.Filled.ThumbUp,
            unSelectedIcon = Icons.Default.ThumbUp,
            route = Routes.SPELL_SCREEN
        )
    )


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
//            DrawerContainer(
//                navigator = navigator,
//                items = items,
//                selectedIndex = 0
//            )
        }
    ) {
        Scaffold(
            topBar = {
                AppToolBar(
                    title = "Movies App",
                    onMenuClick = {
//                        scope.launch {
//                            drawerState.open()
//                        }
                    },
                    onSearchClick = {
                        startSearch = it
                        println("---------------------------------<$startSearch>")
                    }
                )
            }
        ) { paddingValues->
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn (
                    modifier = Modifier
                        .padding(horizontal = (screenWidth * .03).dp, vertical = 10.dp)
                ){
                    item {
                        SearchField(
                            isVisible =  startSearch,
                            modifier = Modifier
                                .background(Color.Transparent),
                        ) {
                            isSearching = it.isNotEmpty() || it != ""
                            viewModel.searchMovieCharacters(it)
                        }
                        if (!isSearching){
                            MovieCover(navigator = navigator)
                        }
                        Text(
                            text = "Characters",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = DarkGreen,
                            modifier = Modifier
                                .padding(vertical = 20.dp)
                        )
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize(),
                        ){
                            if (isLoading){
                                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                            }
                            if (loadingError.isNotEmpty()){
                                RetrySection(error = loadingError) {
                                    viewModel.loadCharacters()
                                }
                            }
                        }
                    }

                    items(characters.size){index->
                        CharacterContainer(
                            character = characters[index],
                            navigator = navigator,
                            modifier = Modifier
                                .padding(vertical = 3.dp)
                        )
                    }


                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContainer(
    navigator: NavController,
    selectedIndex: Int,
    items:List<MenuItem>,
) {
    ModalDrawerSheet {
        Spacer(modifier = Modifier.height(150.dp))
        items.forEachIndexed { index, menuItem ->
            Box(
                modifier = Modifier
                    .fillMaxWidth(.75f)
                    .padding(vertical = 10.dp, horizontal = 3.dp)
                    .background(
                        color = if (selectedIndex == index) DarkGreen.copy(alpha = .1f) else Color.White,
                        shape = RoundedCornerShape(3.dp)
                    )
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 5.dp),
                    verticalAlignment = CenterVertically
                ) {
                    Icon(
                        imageVector = if(selectedIndex == index) menuItem.selectedIcon else menuItem.unSelectedIcon,
                        contentDescription = null
                    )
                    Text(
                        text = menuItem.title
                    )
                }
            }
        }
    }
}


@Composable
fun RetrySection(
    error: String,
    onRetry:()-> Unit
) {
    Column {
        Text(text = error, color = Color.Red, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .background(color = DarkGreen, shape = CircleShape)
                .align(Alignment.CenterHorizontally)
        ){
            AppIconButton(
                onClick = {
                    onRetry()
                },
                icon = Icons.Default.Refresh,
                iconColor = Color.White
            )
        }
    }
}