package com.elijahverdoorn.chronogg.data

import com.gargoylesoftware.htmlunit.*
import com.gargoylesoftware.htmlunit.html.HtmlPage
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager

class WebRenderer {
    fun getPageBody(url: String): String {
        val manager = PoolingHttpClientConnectionManager()
        manager.maxTotal = 1
        val client = WebClient(BrowserVersion.BEST_SUPPORTED)
        var page: HtmlPage? = null
        client.options.apply {
            isCssEnabled = false
            isJavaScriptEnabled = true
            isAppletEnabled = false
            isDownloadImages = false

            isThrowExceptionOnScriptError = false
            isThrowExceptionOnFailingStatusCode = false

            historySizeLimit = 0
            historyPageCacheLimit = 0
            timeout = 0
            maxInMemory = 100000000
        }

       try {
            page = client.getPage(url)
            client.waitForBackgroundJavaScript(10000)
            println("ELIJAH page: " + (page as HtmlPage).asXml())
        } catch (e: OutOfMemoryError) {
            println("ELIJAH OOM")
            println(page?.asXml()?:"PAGE IS NULL")
        } finally {
            client.close()
            println("ELIJAH RETURNING: ${if (page != null) "a page" else "nothing"}")
            return page?.asXml()?:""
        }
    }
}