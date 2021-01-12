package com.adedom.jsoup.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TimelineResponse(
    @SerialName("Data") val `data`: List<Data> = emptyList(),
    @SerialName("DevBy") val devBy: String? = null,
    @SerialName("SeverBy") val severBy: String? = null,
    @SerialName("Source") val source: String? = null,
    @SerialName("UpdateDate") val updateDate: String? = null,
)
