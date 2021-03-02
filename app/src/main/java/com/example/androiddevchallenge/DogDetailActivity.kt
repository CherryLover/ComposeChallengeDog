/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Dog
import com.example.androiddevchallenge.ui.pageView

/**
 * @description
 * @author: Created jiangjiwei in 2021/3/2 6:56 下午
 */
class DogDetailActivity : AppCompatActivity() {

    companion object {
        const val KEY_DOG_OBJ = "DOG_OBJ"
        const val REQ_START = 101

        @JvmStatic
        fun start(context: AppCompatActivity, dog: Dog) {
            val starter = Intent(context, DogDetailActivity::class.java)
            starter.putExtra(KEY_DOG_OBJ, dog)
            context.startActivityForResult(starter, REQ_START)
        }
    }

    var cDoge: Dog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dog: Dog = (intent.getSerializableExtra(KEY_DOG_OBJ) ?: return) as Dog
        cDoge = dog
        setContent {
            MyDetailView(dog = dog)
        }
    }

    override fun onBackPressed() {
        cDoge?.let {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(KEY_DOG_OBJ, it)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}

@Composable
fun MyDetailView(dog: Dog) {
    pageView("${dog.nickname} Info") {
        Column(modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp)) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(1F)
                    .aspectRatio(1F, true),
                contentScale = ContentScale.Crop, painter = painterResource(id = dog.img), contentDescription = "Picture for ${dog.type} named ${dog.nickname}"
            )
            Row(modifier = Modifier.padding(vertical = 10.dp), verticalAlignment = Alignment.CenterVertically) {
                val feed = remember { mutableStateOf(false) }
                Column {
                    Text(text = dog.nickname, style = MaterialTheme.typography.h6, color = MaterialTheme.colors.primary)
                    Text(text = "Breed: ${dog.type}\nAge: ${dog.monthAge} Months", style = MaterialTheme.typography.body1, color = MaterialTheme.colors.onSecondary)
                }
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                    if (feed.value) {
                        Button(
                            onClick = {
                                feed.value = !feed.value
                                dog.feed = feed.value
                            }
                        ) {
                            Text(text = "Sell", color = Color.White)
                        }
                    } else {
                        Button(
                            onClick = {
                                feed.value = !feed.value
                                dog.feed = feed.value
                            }
                        ) {
                            Text(text = "FEED ME", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyDetailViewPreview() {
    MyDetailView(dog = Dog("baby", "German Shepherd", R.drawable.ic_dog_heibai, 12, Color.Black, 1))
}
