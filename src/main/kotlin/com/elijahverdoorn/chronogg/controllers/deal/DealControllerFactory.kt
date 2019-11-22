package com.elijahverdoorn.chronogg.controllers.deal

import com.elijahverdoorn.chronogg.data.ChronoRssReader

class DealControllerFactory {
    fun createDealController() = DealController(ChronoRssReader())
}