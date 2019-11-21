package com.elijahverdoorn.chronogg.controllers.deal

import com.elijahverdoorn.chronogg.api.ChronoGgApi
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DealController(val api: ChronoGgApi) {
    @GetMapping("/deal")
    fun deal() = api.latestDeal
}