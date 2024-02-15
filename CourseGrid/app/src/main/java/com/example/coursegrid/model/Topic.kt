package com.example.coursegrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val topicTitleResource: Int,
    val viewCount: Int = (500..600).random(),
    @DrawableRes val topicImageResourceId: Int,


    )
