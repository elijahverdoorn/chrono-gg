import models.Deal

fun main() {
    val latestDeal = Deal()
    println("Title: ${latestDeal.title}")
    println("Date: ${latestDeal.date}")
    println("Price: ${latestDeal.price}")
}