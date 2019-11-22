package com.elijahverdoorn.chronogg.controllers.deal

import com.elijahverdoorn.chronogg.data.ChronoRssReader
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DealController(val api: ChronoRssReader) {
    @GetMapping("/deal")
    fun deal() = api.latestDeal
}