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
package com.example.androiddevchallenge.data

import androidx.compose.ui.graphics.Color
import java.io.Serializable

/**
 * @description
 * @author: Created jiangjiwei in 2021/3/2 1:05 下午
 */
class Dog(
    var nickname: String,
    // 品种
    var type: String,
    var img: Int,
    var monthAge: Int,
    // 毛色
    var color: Color,
    var gender: Int
) : Serializable {
    // 领养
    var feed: Boolean = false
    override fun toString(): String {
        return "Dog(nickname='$nickname', type='$type', img=$img, monthAge=$monthAge, color=$color, gender=$gender)"
    }
}
