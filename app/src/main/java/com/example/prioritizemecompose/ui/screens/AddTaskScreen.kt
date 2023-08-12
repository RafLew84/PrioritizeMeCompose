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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prioritizemecompose.data.db.Priority
import com.example.prioritizemecompose.data.db.Task
import com.example.prioritizemecompose.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(onHome: () -> Unit, viewModel: TaskViewModel){

    val radioOptions = Priority.values().toList()
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1] ) }
    val checkedState = remember { mutableStateOf(true) }
    val enabled = remember { mutableStateOf(true) }

    var task by remember {
        mutableStateOf(Task(title = "", description = ""))
    }
    
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
    ) {
        OutlinedTextField(
            value = task.title,
            onValueChange = { task = task.copy(title = it) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = { Text(text = "Tytuł")}
        )

        OutlinedTextField(
            value = task.description,
            onValueChange = { task = task.copy(description = it) },
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
                    .fillMaxWidth(.6f)
                    .padding(end = 2.dp),
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    radioOptions.forEach {text ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = (text == selectedOption),
                                    onClick = {
                                        onOptionSelected(text)
                                    },
                                    enabled = enabled.value
                                )
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (text == selectedOption),
                                onClick = { onOptionSelected(text) },
                                enabled = enabled.value
                            )
                            TextField(
                                value = text.toString(),
                                onValueChange = {},
                                modifier = Modifier.padding(start = 16.dp),
                                enabled = enabled.value,
                                singleLine = true,
                                readOnly = true,
                                textStyle = TextStyle.Default.copy(fontSize = 16.sp),
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent,
                                    containerColor = Color.White
                                )
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
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 4.dp)
                    )

                    Checkbox(
                        modifier = Modifier
                            .scale(1.2f)
                            .padding(top = 8.dp),
                        checked = task.isDone,
                        onCheckedChange = {
                            checkedState.value = it
                            task = task.copy(isDone = it)
                            if (checkedState.value) {
                                enabled.value = false
                                onOptionSelected(Priority.WYKONANY)
                            }
                            else
                                enabled.value = true
                        }
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
                onClick = {
                    if (task.title.isNotEmpty() && task.description.isNotEmpty())
                        viewModel.addTask(task)
                    onHome()
                          },
                modifier = Modifier.fillMaxWidth(.5f)
            ) {
                Text(
                    text = "Zapisz",
                    fontSize = 20.sp
                )
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
}