package com.example.androiddevchallenge

import androidx.compose.ui.graphics.Color
import com.example.androiddevchallenge.data.Dog

/**
 * @description
 * @author: Created jiangjiwei in 2021/3/2 1:08 下午
 */
object DogStore {
    private val dogList = mutableListOf<Dog>()

    fun getDogList(): MutableList<Dog> {
        if (dogList.isNullOrEmpty()) {
            dogList.add(Dog("baby", "German Shepherd", R.drawable.ic_dog_heibai, 12, Color.Black, 1))
            dogList.add(Dog("lulu", "Labrador Retriever", R.drawable.ic_dog_labuladuo, 8, Color.Yellow, 0))
            dogList.add(Dog("haha", "Siberian husky", R.drawable.ic_dog_hashiqi, 20, Color.White, 1))
            dogList.add(Dog("dudu", "Doberman", R.drawable.ic_dog_dubin, 13, Color.DarkGray, 0))

            dogList.add(Dog("sharp", "German Shepherd", R.drawable.ic_dog_heibai, 12, Color.Black, 0))
            dogList.add(Dog("andrew", "Labrador Retriever", R.drawable.ic_dog_labuladuo, 8, Color.White, 1))
            dogList.add(Dog("maris", "Siberian husky", R.drawable.ic_dog_hashiqi, 20, Color.Cyan, 1))
            dogList.add(Dog("kangkang", "Doberman", R.drawable.ic_dog_dubin, 13, Color.Blue, -1))
        }
        return dogList
    }
}