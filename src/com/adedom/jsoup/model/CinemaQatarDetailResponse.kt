package com.adedom.jsoup.model

import kotlinx.serialization.Serializable

@Serializable
data class CinemaQatarDetailResponse(
    var success: Boolean = false,
    var message: String? = null,
    var cinemaQatarDetail: CinemaQatarDetail? = null,
)
