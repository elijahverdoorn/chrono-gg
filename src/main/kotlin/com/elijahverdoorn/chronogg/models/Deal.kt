package com.elijahverdoorn.chronogg.models

import java.util.*

const val CHRONO_GG_URL = "https://chrono.gg"

data class Deal(
    val title: String, // The title of the game on sale
    val date: Date, // The full date of the sale
    val price: Float, // The price of the game for sale, in USD
    val communityLink: String, // A link to the chrono.gg community page for this deal
    val description: String, // HTML string of the description field
    val imgUrl: String, // link to the image associated with this deal
    val link: String = CHRONO_GG_URL, // A link to chrono.gg main page
    val timestamp: Date = Date() // The time that this record was created.
)
