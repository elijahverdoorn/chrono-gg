package com.elijahverdoorn.chronogg.controllers.deal

import com.elijahverdoorn.chronogg.api.ChronoGgApi

class DealControllerFactory {
    fun createDealController() = DealController(ChronoGgApi())
}