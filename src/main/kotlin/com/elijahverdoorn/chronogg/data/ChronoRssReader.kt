package com.elijahverdoorn.chronogg.data

import com.apptastic.rssreader.Item
import com.apptastic.rssreader.RssReader
import com.elijahverdoorn.chronogg.models.Deal
import java.text.SimpleDateFormat
import java.util.stream.Collectors

class ChronoRssReader {
    val reader = RssReader()
    val latestItem: Item by lazy {
        getLatestDealItem()
    }

    val latestDeal: Deal by lazy {
        getLatestDealString(latestItem).let {
            Deal(
                title = parseTitle(it),
                date = parseDate(it),
                price = parsePrice(it),
                communityLink = parseLink(latestItem)
            )
        }
    }

    private fun parseDate(str: String) =
        SimpleDateFormat("MM/dd/yy").parse(str.substringAfter("-").substringBefore("-").trim())

    private fun parsePrice(str: String) = str.substringAfterLast("$").trim().toFloat()

    private fun parseTitle(str: String) = str.substringBefore("-").trim()

    private fun getLatestDealString(item: Item) = item.title.orElse("")

    private fun getLatestDealItem(): Item {
        return reader.read(RSS_FEED_URL)
                .collect(Collectors.toList())
                .first()
    }

    private fun parseLink(item: Item) = item.link.orElse("")

    companion object {
        const val RSS_FEED_URL = "https://community.chrono.gg/c/daily-deals.rss"
    }
}