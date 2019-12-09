package com.elijahverdoorn.chronogg.data

import com.apptastic.rssreader.Item
import com.apptastic.rssreader.RssReader
import com.elijahverdoorn.chronogg.constants.CHRONO_GG_URL
import com.elijahverdoorn.chronogg.constants.RSS_FEED_URL
import com.elijahverdoorn.chronogg.models.Deal
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.util.*
import java.util.stream.Collectors

class ChronoSoupParser {
    var mainPageSoup: Element = Jsoup.parse(WebRenderer().getPageBody(CHRONO_GG_URL)) // The "soup" from the main chrono.gg page
    var rssFeedItem: Item = getLatestDealItem() // the latest item published to the RSS feed (easy to get the image this way)

    val latestDeal: Deal by lazy {
        Deal(
            title = parseTitle(mainPageSoup),
            date = Date(),
            price = parseSalePrice(mainPageSoup),
            communityLink = parseCommunityLink(mainPageSoup),
            imgUrl = parseImage(rssFeedItem),
            steamPrice = parseSteamPrice(mainPageSoup)
        )
    }

    /**
     * Gets the latest deal item from the RSS feed
     */
    private fun getLatestDealItem(): Item {
        return RssReader().read(RSS_FEED_URL)
                .collect(Collectors.toList())
                .first()
    }

    /**
     * Parses the image URL from the RSS feed item fetched by getLatestDealItem()
     */
    private fun parseImage(item: Item): String {
        val document = Jsoup.parse(item.description.get())
        document.getElementsByTag("img").forEach {
            if (it.hasAttr("width") && it.hasAttr("height")) {
                if (it.hasAttr("alt")) {
                    return it.attr("src")
                }
            }
        }
        return ""
    }

    /**
     * Parses the game title from the main page soup
     */
    private fun parseTitle(doc: Element): String {
        doc.getElementsByClass("game-hero-logo").forEach {
            return it.attr("title")
        }
        return ""
    }

    /**
     * Parses the game's sale price from the main page soup
     */
    private fun parseSalePrice(doc: Element): Float {
        doc.getElementsByClass("btn btn--buy-now").forEach {
            return it.getElementById("price").text().trim().substring(1).toFloat() // The string has a leading "$" character - remove it with .substring() or else app will fail to parse
        }
        return -1.toFloat()
    }

    /**
     * Parses the current price of today's deal on Steam (used to calculate the discount) from the main page soup
     */
    private fun parseSteamPrice(doc: Element): Float {
        doc.getElementsByClass("list-price").forEach {
            it.getElementsByClass("strikethrough").forEach {
                return it.text().trim().substring(1).toFloat() // Remove leading "$" character and any whitespace to allow for float parsing
            }
        }
        return -1.toFloat()
    }

    /**
     * Parses a link to the chrono.gg community page for this game from the main page soup
     */
    private fun parseCommunityLink(doc: Element): String {
        doc.getElementsByClass("btn center-text community-button").forEach {
            return it.attr("href")
        }
        return ""
    }
}