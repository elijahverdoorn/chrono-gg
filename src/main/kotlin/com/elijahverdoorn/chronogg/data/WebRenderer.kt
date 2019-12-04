package com.elijahverdoorn.chronogg.data

import com.elijahverdoorn.chronogg.constants.CHRONO_GG_URL
import com.gargoylesoftware.htmlunit.BrowserVersion
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlPage

class WebRenderer {
    fun getPageBody(url: String): String {
        val client = WebClient(BrowserVersion.BEST_SUPPORTED)
        val page = client.getPage<HtmlPage>(url)
        client.close()

        return page.asXml()
    }
}