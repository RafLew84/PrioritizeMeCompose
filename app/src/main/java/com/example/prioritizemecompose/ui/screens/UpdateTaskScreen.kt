package com.example.prioritizemecompose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTaskScreen(onHome: () -> Unit){
    
    val radioOptions = listOf("Low", "Normal", "High")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1] ) }
    val checkedState = remember { mutableStateOf(true) }
    
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = { Text(text = "Tytuł")}
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.5f),
            singleLine = false,
            label = { Text(text = "Opis")}
        )
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
        ) {
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .padding(end = 2.dp),
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    radioOptions.forEach { text ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = (text == selectedOption),
                                    onClick = {
                                        onOptionSelected(text)
                                    }
                                )
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (text == selectedOption),
                                onClick = { onOptionSelected(text) }
                            )
                            Text(
                                text = text,
                                style = MaterialTheme.typography.bodyMedium.merge(),
                                modifier = Modifier.padding(start = 16.dp),
                                fontSize = 24.sp
                            )
                        }
                    }
                }
            }

            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(144.dp)
                    .padding(start = 2.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Czy zakończone",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(start = 4.dp)
                    )

                    Checkbox(
                        modifier = Modifier
                            .scale(1.5f)
                            .padding(top = 8.dp),
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it }
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {


            Button(
                onClick = onHome,
                modifier = Modifier.fillMaxWidth(.5f).padding(end = 2.dp)
            ) {
                Text(
                    text = "Aktualizuj",
                    fontSize = 20.sp
                )
            }
            Button(
                onClick = onHome,
                modifier = Modifier.fillMaxWidth().padding(start = 2.dp)
            ) {
                Text(
                    text = "Usuń",
                    fontSize = 20.sp
                )
            }
        }
        OutlinedButton(
            onClick = onHome,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Wróć",
                fontSize = 20.sp
            )
        }
    }
}