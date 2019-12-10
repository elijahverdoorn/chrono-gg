@file:JvmName("CronApplicationMain")

package com.elijahverdoorn.chronogg

import com.elijahverdoorn.chronogg.data.ChronoSoupParser
import com.google.gson.Gson
import com.google.gson.JsonParser
import java.io.File
import java.lang.Exception

class CronApplication(
    var configFileLocation: String = CONFIG_FILE_LOCATION
) {
    fun main() {
//        val deal = ChronoRssReader().latestDeal
        val deal = ChronoSoupParser().latestDeal
        val json = Gson().toJson(deal)
        File(loadFileTarget()).writeText(json)
    }

    private fun loadFileTarget(): String {
        var fileLocation = DEFAULT_FILE_TARGET
        try {
            File(configFileLocation).apply {
                if (canRead()) {
                    val text = readText()
                    fileLocation = JsonParser.parseString(text).asJsonObject.get(CONFIG_PROPERTY).asString
                }
            }
        } catch (e: Exception) {
            println("Exception reading from config file. Falling back to default.")
            println(e)
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

fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        // if the user passed us a command line location for their config file, use that
        CronApplication(args[0]).main()
    } else {
        // otherwise use the default config file location
        CronApplication().main()
    }
}