package models

import com.apptastic.rssreader.RssReader
import java.text.SimpleDateFormat
import java.util.*
import java.util.stream.Collectors

class Deal {
    val reader = RssReader()

    private val latestDealFromRss: String by lazy {
        getLatestDeal()
    }
    val title: String by lazy {
        latestDealFromRss.substringBefore("-").trim()
    }
    val date: Date by lazy {
        SimpleDateFormat("MM/dd/yy").parse(latestDealFromRss.substringAfter("-").substringBefore("-").trim())
    }
    val price: Float by lazy {
        latestDealFromRss.substringAfterLast("$").trim().toFloat()
    }

    private fun getLatestDeal(): String {
        return reader.read(RSS_FEED_URL).collect(Collectors.toList()).first().title.orElseGet { "" }
    }

    companion object {
        const val RSS_FEED_URL = "https://community.chrono.gg/c/daily-deals.rss"
    }
}