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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.prioritizemecompose.data.db.Priority
import com.example.prioritizemecompose.data.db.Task
import com.example.prioritizemecompose.viewmodel.TaskViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    onAddScreen: () -> Unit,
    onEditScreen: () -> Unit,
    viewModel: TaskViewModel
) {

    val tasks by viewModel.tasksState.collectAsStateWithLifecycle()
    var filteredTitle by remember {
        mutableStateOf("")
    }

    val selected = remember { mutableStateOf(Priority.NORMALNY) }

    Scaffold(
        floatingActionButton = {
            AddTaskFAB(onClick = { onAddScreen() })
        },
        topBar = {
            TopAppBar(
                title = {
                    FilteringEditText(filteredTitle) { title ->
                        filteredTitle = title
                        viewModel.filterByTitle(filteredTitle)
                    }
                },
                actions = { PriorityFiltering(selected, viewModel)},
                modifier = Modifier.height(60.dp)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 12.dp)
        ) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(200.dp),
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                content = {
                    items(tasks.size) { index ->
                        CreateItemCard(
                            onClickHigherPriority = { /*TODO()*/ },
                            onClickDone = { /*TODO()*/ },
                            onClickLowerPriority = { /*TODO()*/ },
                            onClickEdit = onEditScreen,
                            task = tasks[index]
                        )
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun AddTaskFAB(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(10.dp),
        containerColor = Color(100, 149, 237),
        shape = FloatingActionButtonDefaults.smallShape
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add item"
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun FilteringEditText(filteredTitle: String, onValueChange: (String) -> Unit) {
    TextField(
        value = filteredTitle,
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        onValueChange = onValueChange,
        label = { Text(text = "Filtruj") },
        textStyle = TextStyle.Default.copy(fontSize = 20.sp)
    )
}

@Composable
private fun PriorityFiltering(
    selected: MutableState<Priority>,
    viewModel: TaskViewModel
) {
    val expanded = remember { mutableStateOf(false) }

    FilledTonalIconButton(
        onClick = { expanded.value = true },
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color(100, 149, 237)
        ),
        shape = FloatingActionButtonDefaults.smallShape,
        modifier = Modifier.padding(top = 10.dp, start = 4.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Filter,
            contentDescription = "Sorting Options",
            tint = Color.Black
        )
    }

    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
    ) {
        viewModel.priorities.forEach { option ->
            DropdownMenuItem(
                text = { Text(option.toString())
                },
                onClick = {
                    expanded.value = false
                    selected.value = option
                    viewModel.filteredByPriority(option.toString())
                }
            )
        }
    }
}

@Composable
private fun CreateItemCard(
    onClickHigherPriority: () -> Unit,
    onClickDone: () -> Unit,
    onClickLowerPriority: () -> Unit,
    onClickEdit: () -> Unit,
    task: Task
) {
    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CardTitle(task.title)
            Text(
                text = task.description,
                modifier = Modifier.padding(4.dp),
                fontSize = 14.sp
            )

            ButtonsRow(
                onClickHigherPriority = onClickHigherPriority,
                onClickDone = onClickDone,
                onClickLowerPriority = onClickLowerPriority,
                onClickEdit = onClickEdit
            )

        }
    }
}

@Composable
private fun CardTitle(title: String) {
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
                text = title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
            )

            CreatePriorityIcon(Color(128, 203, 196))
        }
    }
}

@Composable
private fun CreatePriorityIcon(color: Color) {
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
                colorFilter = ColorFilter.tint(color)
            )
        }
    }
}

@Composable
private fun ButtonsRow(
    onClickHigherPriority: () -> Unit,
    onClickDone: () -> Unit,
    onClickLowerPriority: () -> Unit,
    onClickEdit: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CreateOutlinedIconButton(
                onClick = onClickHigherPriority,
                icon = Icons.Outlined.KeyboardArrowUp
            )
            CreateOutlinedIconButton(
                onClick = onClickDone,
                icon = Icons.Outlined.Done
            )
            CreateOutlinedIconButton(
                onClick = onClickLowerPriority,
                icon = Icons.Outlined.KeyboardArrowDown
            )

            OutlinedIconButton(
                onClick = { /* doSomething() */ },
                shape = FloatingActionButtonDefaults.smallShape,
                modifier = Modifier.padding(start = 4.dp),
                border = BorderStroke(2.dp, Color(0xFF6650a4)),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color(0xFF6650a4),
                ),
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete",
                    tint = Color(0xAA90021F)
                )
            }
        }
        OutlinedIconButton(
            onClick = onClickEdit,
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp),
            border = BorderStroke(2.dp, Color(0xFF6650a4)),
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = Color(0xFF6650a4),
            ),
            content = {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Edit task"
                )
            }
        )
    }
}

@Composable
private fun CreateOutlinedIconButton(
    onClick: () -> Unit,
    icon: ImageVector
) {
    OutlinedIconButton(
        onClick = onClick,
        enabled = true,
        modifier = Modifier.size(40.dp),
        border = BorderStroke(2.dp, Color(0xFF6650a4)),
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = Color(0xFF6650a4),
        ),
        content = {
            Icon(
                imageVector = icon,
                contentDescription = "Lower priority"
            )
        }
    )
}