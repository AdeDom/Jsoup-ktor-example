package com.adedom.jsoup

import com.adedom.jsoup.model.*
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import org.jsoup.Jsoup

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {

    // start project
    install(DefaultHeaders)
    install(CallLogging)

    // gson convertor json
    install(ContentNegotiation) {
        json()
    }

    // route
    install(Routing) {

        get("/test") {
            val response = mapOf("hello" to "world")
            call.respond(response)
        }

        get("/cinema-qatar") {
            val response = CinemaQatarResponse()

            val document = Jsoup.connect("https://www.cinemaqatar.com/").get()
            val data = document.getElementsByClass("thumbnail")

            val cinemaQatarList = mutableListOf<CinemaQatar>()
            for (i in 0 until data.size) {
                val imgUrl = data[i]
                    .select("img")
                    .attr("src")

                val title = data[i]
                    .select("h4.gridminfotitle")
                    .select("span")
                    .text()

                val detail = data[i]
                    .select("h4.gridminfotitle")
                    .select("a")
                    .attr("href")
                val movieDetail = document.baseUri() + detail

                cinemaQatarList.add(CinemaQatar(title = title, movieDetail = movieDetail, imgUrl = imgUrl))
            }

            response.success = true
            response.message = "Fetch cinema qatar success"
            response.cinemaQatarList = cinemaQatarList

            call.respond(response)
        }

        get("/cinema-qatar-detail") {
            val movieId = call.parameters["movieId"]

            val response = CinemaQatarDetailResponse()

            val url = "https://www.cinemaqatar.com/showtimes/?movie=$movieId"
            val document = Jsoup.connect(url).get()

            // title
            val title = document.select("div.leftboxtitle")
                .text()
                .trim()

            // imgUrl
            val imgUrl = document.select("div.detailinfoimg")
                .select("img")
                .attr("data-src")


            val detailInfo = document.select("div.detailinfo")
                .text()

            // starCastList
            val starCastText = detailInfo.substringAfter("Star Cast: ").substringBefore("Director")
            val starCastSplit = starCastText.split(",")
            val starCastList = mutableListOf<StarCast>()
            starCastSplit.forEach { starCastList.add(StarCast(it.trim())) }

            // directorList
            val directorText = detailInfo.substringAfter("Director: ").substringBefore("Watch Trailer")
            val directorSplit = directorText.split(",")
            val directorList = mutableListOf<Director>()
            directorSplit.forEach { directorList.add(Director(it.trim())) }

            val cinemaQatarDetail = CinemaQatarDetail(
                title = title,
                imgUrl = imgUrl,
                starCastList = starCastList,
                directorList = directorList,
            )

            response.success = true
            response.message = "Fetch cinema qatar detail success"
            response.cinemaQatarDetail = cinemaQatarDetail

            call.respond(response)
        }

        get("covid19-1") {
            val response: TodayResponse = getHttpClientApache().get("https://covid19.th-stat.com/api/open/today")
            call.respond(response)
        }

        get("covid19-2") {
            val response: TimelineResponse = getHttpClientApache().get("https://covid19.th-stat.com/api/open/timeline")
            call.respond(response)
        }

    }

}

internal fun getHttpClientApache() = HttpClient(Apache) {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }

    install(HttpTimeout) {
        requestTimeoutMillis = 60_000
    }

    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.HEADERS
    }
}
