package com.elijahverdoorn.chronogg.com.elijahverdoorn.chronogg

import com.elijahverdoorn.chronogg.controllers.deal.DealControllerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
class Application {
    @Bean
    fun controller() = DealControllerFactory().createDealController()
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}