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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Dog
import com.example.androiddevchallenge.ui.pageView
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(DogStore.getDogList()) {
                    DogDetailActivity.start(this, it)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == DogDetailActivity.REQ_START && resultCode == RESULT_OK) {
            val dog: Dog = data?.getSerializableExtra(DogDetailActivity.KEY_DOG_OBJ) as Dog
            for (dogStoreDog in DogStore.getDogList()) {
                if (dogStoreDog.nickname == dog.nickname && dogStoreDog.type == dog.type) {
                    dogStoreDog.feed = dog.feed
                }
            }
            setContent {
                MyApp(DogStore.getDogList()) {
                    DogDetailActivity.start(this, it)
                }
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(dogList: List<Dog> = mutableListOf(), onDogItemClick: (dog: Dog) -> Unit) {
    pageView("DogList") {
        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
            items(items = dogList) { dog ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 0.dp, vertical = 4.dp)
                        .fillMaxWidth(1F)
                        .clickable {
                            onDogItemClick(dog)
                        }
                ) {
                    Image(painter = painterResource(id = dog.img), modifier = Modifier.size(120.dp, 90.dp), contentScale = ContentScale.Crop, contentDescription = "Picture for ${dog.type} named ${dog.nickname}")
                    Column(modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp)) {
                        Text(text = dog.nickname, style = MaterialTheme.typography.h6, color = MaterialTheme.colors.primary)
                        Text(text = "Breed: ${dog.type}\nAge: ${dog.monthAge} Months", style = MaterialTheme.typography.body1, color = MaterialTheme.colors.onSecondary)
                        if (dog.feed) {
                            Text(text = "I have owner!", color = MaterialTheme.colors.onSecondary)
                        } else {
                            Text(text = "Pick me !!!", color = MaterialTheme.colors.primaryVariant)
                        }
                    }
                }
            }
        }
    }
}

@Preview("Light Theme", showBackground = true)
@Composable
fun LightPreview() {
    MyTheme {
        val dogList = mutableListOf<Dog>()
        dogList.add(Dog("baby", "German Shepherd", R.drawable.ic_dog_heibai, 12, Color.Black, 1))
        dogList.add(Dog("lulu", "Labrador Retriever", R.drawable.ic_dog_labuladuo, 8, Color.Yellow, 0))
        dogList.add(Dog("haha", "Siberian husky", R.drawable.ic_dog_hashiqi, 20, Color.White, 1))
        dogList.add(Dog("dudu", "Doberman", R.drawable.ic_dog_dubin, 13, Color.DarkGray, 0))

        dogList.add(Dog("sharp", "German Shepherd", R.drawable.ic_dog_heibai, 12, Color.Black, 0))
        dogList.add(Dog("andrew", "Labrador Retriever", R.drawable.ic_dog_labuladuo, 8, Color.White, 1))
        dogList.add(Dog("maris", "Siberian husky", R.drawable.ic_dog_hashiqi, 20, Color.Cyan, 1))
        dogList.add(Dog("Jackson", "Doberman", R.drawable.ic_dog_dubin, 13, Color.Blue, -1))
        MyApp(dogList) {
        }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        val dogList = mutableListOf<Dog>()
        dogList.add(Dog("baby", "German Shepherd", R.drawable.ic_dog_heibai, 12, Color.Black, 1))
        dogList.add(Dog("lulu", "Labrador Retriever", R.drawable.ic_dog_labuladuo, 8, Color.Yellow, 0))
        dogList.add(Dog("haha", "Siberian husky", R.drawable.ic_dog_hashiqi, 20, Color.White, 1))
        dogList.add(Dog("dudu", "Doberman", R.drawable.ic_dog_dubin, 13, Color.DarkGray, 0))

        dogList.add(Dog("sharp", "German Shepherd", R.drawable.ic_dog_heibai, 12, Color.Black, 0))
        dogList.add(Dog("andrew", "Labrador Retriever", R.drawable.ic_dog_labuladuo, 8, Color.White, 1))
        dogList.add(Dog("maris", "Siberian husky", R.drawable.ic_dog_hashiqi, 20, Color.Cyan, 1))
        dogList.add(Dog("Jackson", "Doberman", R.drawable.ic_dog_dubin, 13, Color.Blue, -1))
        MyApp(dogList) {
        }
    }
}
