package com.elijahverdoorn.chronogg

import com.elijahverdoorn.chronogg.data.ChronoRssReader
import com.google.gson.Gson
import com.google.gson.JsonParser
import java.io.File
import java.lang.Exception

class CronApplication {
    fun main() {
        val deal = ChronoRssReader().latestDeal
        val json = Gson().toJson(deal)
        File(loadFileTarget()).writeText(json)
    }

    private fun loadFileTarget(): String {
        var fileLocation = DEFAULT_FILE_TARGET
        try {
            File(CONFIG_FILE_LOCATION).apply {
                if (canRead()) {
                    val text = readText()
                    fileLocation = JsonParser.parseString(text).asJsonObject.get(CONFIG_PROPERTY).asString
                }
            }
        } catch (e: Exception) {
            println("Exception reading from config file. Falling back to default.")
        } finally {
            return fileLocation
        }
    }

    companion object {
        const val DEFAULT_FILE_TARGET = "./chronoDailyDeal.json"
        const val CONFIG_FILE_LOCATION = "./config.json"
        const val CONFIG_PROPERTY = "TARGET_FILE"
    }
}

fun main() {
    CronApplication().main()
}