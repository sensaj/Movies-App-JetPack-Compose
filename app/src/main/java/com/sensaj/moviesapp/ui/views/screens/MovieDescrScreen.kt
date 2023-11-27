package com.sensaj.moviesapp.ui.views.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sensaj.moviesapp.R
import com.sensaj.moviesapp.ui.theme.DarkGreen
import com.sensaj.moviesapp.utils.Constants

@Composable
fun MovieDescrScreen(
    navigator: NavController
) {

    Box(modifier = Modifier){
        Column{
            Image(
                painter = painterResource(id = R.drawable.harry_potter_cast),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(bottomStart = 5.dp, bottomEnd = 5.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(start=10.dp)
            ) {
                Text(
                    text="Harry Potter",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 1.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ){
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.video),
                            contentDescription =null,
                            modifier= Modifier
                                .size(15.dp)
                        )
                        Text(
                            text="8 Movies",
                            fontSize = 12.sp
                        )
                    }
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.cinema),
                            contentDescription =null,
                            modifier= Modifier
                                .size(15.dp)
                        )
                        Text(
                            text="PG-13",
                            fontSize = 12.sp
                        )
                    }
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.calendar),
                            contentDescription =null,
                            modifier= Modifier
                                .size(15.dp)
                        )
                        Text(
                            text="2001 - 2011",
                            fontSize = 12.sp
                        )
                    }
                }
                Divider(thickness = .5.dp, modifier = Modifier.padding(vertical = 2.dp))
                Text(
                    text = Constants.HARRY_POTTER_DESCR,
                    fontSize = 11.sp,
                    lineHeight = 1.2.em,
                )
            }
        }
    }
}