package com.nebulov.hluppr.feature_cup.presentation.add_edit_cup.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun AddImage() {

    var imageUri by remember {
        mutableStateOf(Uri.EMPTY)
    }


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            imageUri = it
        })


    Row(modifier = Modifier.fillMaxWidth()) {
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = null,
            modifier = Modifier
                .clickable(onClick = {
                    launcher.launch("image/*")
                })
                .wrapContentSize()
        )
        AsyncImage(
            model = imageUri,
            contentDescription = null,
            modifier = Modifier.height(100.dp)
        )
    }
}

@Composable
fun GalleryLazyList(modifier: Modifier = Modifier,
photoList: List<Uri>) {

    LazyRow(
        modifier = modifier
            .fillMaxSize()
    ) {
        photoList.forEach { photo ->
            item {
                AsyncImage(
                model = photo,
                contentDescription = null,
                modifier = Modifier.height(100.dp)
            )  }
        }
    }
}