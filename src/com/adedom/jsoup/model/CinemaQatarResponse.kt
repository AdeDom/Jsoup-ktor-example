package com.adedom.jsoup.model

import kotlinx.serialization.Serializable

@Serializable
data class CinemaQatarResponse(
    var success: Boolean = false,
    var message: String? = null,
    var cinemaQatarList: List<CinemaQatar> = emptyList(),
)
