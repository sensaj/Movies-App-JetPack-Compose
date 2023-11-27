package com.sensaj.moviesapp.ui.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.sensaj.moviesapp.data.remote.response.Character
import com.sensaj.moviesapp.navigation.Routes
import com.sensaj.moviesapp.ui.theme.DarkGreen
import java.util.Locale


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CharacterContainer(
    character: Character,
    navigator: NavController,
    modifier: Modifier=Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = .5.dp,
                color= DarkGreen.copy(alpha = .3f),
                shape = RoundedCornerShape(3.dp)
            )
//            clickable {
//                navigator.navigate(Routes.CHARACTER_SCREEN)
//            }
    ){
        Column(
            modifier = Modifier
                .padding(5.dp)
        ){
            Row {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .width(70.dp)
                        .height(70.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    contentScale = ContentScale.Crop,
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(character.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Harry Potter",
                    loading = {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.scale(.5f)
                        )
                    },
                )
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                ) {
                    Text(
                        text = character.name,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Row {
                        Text(
                            text = "Actor: ",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkGreen,
                        )
                        Text(
                            text = character.actor,
                            fontSize = 11.sp,
                        )
                    }
                    Row {
                        Text(
                            text = "House: ",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkGreen,
                        )
                        Text(
                            text = character.house.capitalize(Locale.ROOT),
                            fontSize = 11.sp,
                        )
                    }
                    Row {
                        Text(
                            text = "Wand: ",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkGreen,
                        )
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .background(
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(3.dp)
                                )

                        ){
                            Text(
                                text = character.wand.wood.capitalize(Locale.ROOT),
                                fontSize=12.sp,
                                color= DarkGreen,
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .background(
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(3.dp)
                                )

                        ){
                            Text(
                                text = character.wand.core.capitalize(Locale.ROOT),
                                fontSize=12.sp,
                                color= DarkGreen,
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                            )
                        }
                    }
                }
            }
            FlowRow (
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Box(
                    modifier = Modifier
                        .padding(top = 5.dp, end = 5.dp)
                        .background(
                            color = if (character.wizard) DarkGreen else Color.LightGray,
                            shape = RoundedCornerShape(3.dp)
                        )

                ){
                    Text(
                        text = "Wizard",
                        fontSize=12.sp,
                        color=if (character.wizard) Color.LightGray else Color.Black,
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(top = 5.dp, end = 5.dp)
                        .background(
                            color = if (character.hogwartsStudent) DarkGreen else Color.LightGray,
                            shape = RoundedCornerShape(3.dp)
                        )

                ){
                    Text(
                        text = "Hogwarts Student",
                        fontSize=12.sp,
                        color = if (character.hogwartsStudent) Color.LightGray else Color.Black,
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(top = 5.dp, end = 5.dp)
                        .background(
                            color = if (character.hogwartsStaff) DarkGreen else Color.LightGray,
                            shape = RoundedCornerShape(3.dp)
                        )

                ){
                    Text(
                        text = "Hogwarts Staff",
                        fontSize=12.sp,
                        color = if (character.hogwartsStaff) Color.LightGray else Color.Black,
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(top = 5.dp, end = 5.dp)
                        .background(
                            color = if (character.alive) DarkGreen else Color.LightGray,
                            shape = RoundedCornerShape(3.dp)
                        )

                ){
                    Text(
                        text = "Alive",
                        fontSize=12.sp,
                        color = if (character.alive) Color.LightGray else Color.Black,
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    )
                }
            }
        }
    }
}