package com.example.androiddevchallenge.ui

import androidx.compose.material.*
import androidx.compose.runtime.Composable

/**
 * @description
 * @author: Created jiangjiwei in 2021/3/2 12:59 下午
 */
@Composable
fun pageView(title: String = "", content: @Composable () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = title, style = MaterialTheme.typography.h5)
        })
    }) {
        content()
    }
}