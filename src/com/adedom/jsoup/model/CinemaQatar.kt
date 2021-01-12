package com.adedom.jsoup.model

import kotlinx.serialization.Serializable

@Serializable
data class CinemaQatar(
    val title: String? = null,
    val movieDetail: String? = null,
    val imgUrl: String? = null,
)
