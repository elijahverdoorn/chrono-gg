package com.elijahverdoorn.chronogg.api

import com.apptastic.rssreader.RssReader
import com.elijahverdoorn.chronogg.models.Deal
import java.text.SimpleDateFormat
import java.util.stream.Collectors

class ChronoGgApi {
    val reader = RssReader()

    val latestDeal: Deal by lazy {
        getLatestDealString().let {
            Deal(
                    title = parseTitle(it),
                    date = parseDate(it),
                    price = parsePrice(it)
            )
        }
    }

    private fun parseDate(str: String) =
        SimpleDateFormat("MM/dd/yy").parse(str.substringAfter("-").substringBefore("-").trim())

    private fun parsePrice(str: String) = str.substringAfterLast("$").trim().toFloat()

    private fun parseTitle(str: String) = str.substringBefore("-").trim()

    private fun getLatestDealString(): String {
        return reader.read(RSS_FEED_URL).collect(Collectors.toList()).first().title.orElseGet { "" }
    }

    companion object {
        const val RSS_FEED_URL = "https://community.chrono.gg/c/daily-deals.rss"
    }
}