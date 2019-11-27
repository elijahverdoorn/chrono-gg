package com.elijahverdoorn.chronogg.models

import java.util.*

data class Deal(
    val title: String, // The title of the game on sale
    val date: Date, // The full date of the sale
    val price: Float, // The price of the game for sale, in USD
    val timestamp: Date = Date()// The time that this record was created.
)
