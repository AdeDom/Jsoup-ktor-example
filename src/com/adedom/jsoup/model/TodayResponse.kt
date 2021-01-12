package com.adedom.jsoup.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodayResponse(
    @SerialName("Confirmed") val confirmed: Int? = null,
    @SerialName("Deaths") val deaths: Int? = null,
    @SerialName("DevBy") val devBy: String? = null,
    @SerialName("Hospitalized") val hospitalized: Int? = null,
    @SerialName("NewConfirmed") val newConfirmed: Int? = null,
    @SerialName("NewDeaths") val newDeaths: Int? = null,
    @SerialName("NewHospitalized") val newHospitalized: Int? = null,
    @SerialName("NewRecovered") val newRecovered: Int? = null,
    @SerialName("Recovered") val recovered: Int? = null,
    @SerialName("SeverBy") val severBy: String? = null,
    @SerialName("Source") val source: String? = null,
    @SerialName("UpdateDate") val updateDate: String? = null,
)
