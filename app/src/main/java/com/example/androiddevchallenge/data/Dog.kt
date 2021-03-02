package com.example.androiddevchallenge.data

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import java.io.Serializable

/**
 * @description
 * @author: Created jiangjiwei in 2021/3/2 1:05 下午
 */
class Dog(
    var nickname: String,
    //品种
    var type: String,
    var img: Int,
    var monthAge: Int,
    // 毛色
    var color: Color,
    var gender: Int
): Serializable {
    //领养
    var feed: Boolean = false
    override fun toString(): String {
        return "Dog(nickname='$nickname', type='$type', img=$img, monthAge=$monthAge, color=$color, gender=$gender)"
    }
}