package com.elijahverdoorn.chronogg.data

import com.elijahverdoorn.chronogg.constants.CHRONO_GG_URL
import com.elijahverdoorn.chronogg.models.Deal
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class ChronoSoupParser {
    var soup: Document
    val latestDeal: Deal by lazy {
        Deal(
            title = ,
            date = ,
            price = parseSalePrice(soup),
            communityLink = ,
            imgUrl = ,
            steamPrice = parseSteamPrice(soup)
        )
    }

    init {
        soup = Jsoup.parse(CHRONO_GG_URL)
    }

    private fun parseTitle(doc: Document): String {
        doc.getElementsByClass("game-hero-logo").forEach {
            return it.attr("title")
        }
        return ""
    }

    private fun parseSalePrice(doc: Document): String {
        doc.getElementsByClass("btn btn--buy-now").forEach {
            return it.getElementById("price").text()
        }
        return ""
    }

    private fun parseSteamPrice(doc: Document): String {
        doc.getElementsByClass("list-price").forEach {
            it.getElementsByClass("strikethrough").forEach {
                return it.text()
            }
        }
        return ""
    }

    private fun communityLink(doc: Document): String {

    }

    private fun parseImgUrl(doc: Document): String {

    }
}