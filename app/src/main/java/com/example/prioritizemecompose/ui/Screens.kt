package com.example.prioritizemecompose.ui

sealed class Screens(
    val route: String
) {
    object TaskList : Screens("tasklist")
    object Add : Screens("add")
    object Update : Screens("update")
}