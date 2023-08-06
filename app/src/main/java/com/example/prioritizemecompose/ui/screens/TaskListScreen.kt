package com.example.prioritizemecompose.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prioritizemecompose.data.dummydata.DataProvider

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskListScreen(){
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(200.dp),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(DataProvider.tasks.size) { index ->
                Card(
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(147, 176, 230))

                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = DataProvider.tasks[index].title,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .weight(1f),
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center,
                                )

                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .padding(end = 4.dp)
                                )
                                {
                                    Card(
                                        elevation = CardDefaults.cardElevation(10.dp),
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color(0xFF6650a4)
                                        )
                                    ) {
                                        Image(
                                            imageVector = Icons.Default.Circle,
                                            contentDescription = "Priority",
                                            colorFilter = ColorFilter.tint(Color(128, 203, 196))
                                        )
                                    }
                                }
                            }
                        }
                        Text(
                            text = DataProvider.tasks[index].description,
                            modifier = Modifier.padding(4.dp),
                            fontSize = 14.sp
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, bottom = 8.dp),
                            Arrangement.SpaceEvenly
                        ) {
                            OutlinedIconButton(
                                onClick = { /*TODO*/ },
                                enabled = true,
                                modifier = Modifier.size(30.dp),
                                border = BorderStroke(2.dp, Color(0xFF6650a4)),
                                colors = IconButtonDefaults.iconButtonColors(
                                    contentColor = Color(0xFF6650a4),
                                ),
                                content = {
                                    Icon(
                                        imageVector = Icons.Outlined.KeyboardArrowUp,
                                        contentDescription = "Higher priority"
                                    )
                                }
                            )
                            OutlinedIconButton(
                                onClick = { /*TODO*/ },
                                enabled = true,
                                modifier = Modifier.size(30.dp),
                                colors = IconButtonDefaults.iconButtonColors(
                                    contentColor = Color(0xFF6650a4),
                                ),
                                border = BorderStroke(2.dp, Color(0xFF6650a4)),
                                content = {
                                    Icon(
                                        imageVector = Icons.Outlined.Done,
                                        contentDescription = " Done"
                                    )
                                }
                            )
                            OutlinedIconButton(
                                onClick = { /*TODO*/ },
                                enabled = true,
                                modifier = Modifier.size(30.dp),
                                border = BorderStroke(2.dp, Color(0xFF6650a4)),
                                colors = IconButtonDefaults.iconButtonColors(
                                    contentColor = Color(0xFF6650a4),
                                ),
                                content = {
                                    Icon(
                                        imageVector = Icons.Outlined.KeyboardArrowDown,
                                        contentDescription = "Lower priority"
                                    )
                                }
                            )
                        }
                    }
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}