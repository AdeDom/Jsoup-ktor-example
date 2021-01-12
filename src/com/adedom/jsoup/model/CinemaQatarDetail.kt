package com.adedom.jsoup.model

import kotlinx.serialization.Serializable

@Serializable
data class CinemaQatarDetail(
    val title: String? = null,
    val imgUrl: String? = null,
    val starCastList: List<StarCast> = emptyList(),
    val directorList: List<Director> = emptyList(),
)
