# Deprecation Notice

As a result of [this announcement posted to Twitter](https://twitter.com/chronodeals/status/1316771285167419394?s=20), Chrono.gg will be shutting down. As a result, development of this project will halt and the action will be disabled. Thank you for using Chrono Daily Deal.


# Chrono.gg API

A simple application that wraps the Deal-of-the-Day RSS system found on chrono.gg into a REST API, or a daily script to pull the deal from the feed and wrap it in a convenient JSON file.

To see a sample of the JSON output, visit [https://elijahverdoorn.com/chronogg/deal.json](https://elijahverdoorn.com/chronogg/deal.json)

## Technologies
- Kotlin
- Spring Boot
- Cron
- RSS
- HTMLUnit
- JSoup

## Usage
The application can be run either as a Spring Boot REST service, or as a simple on-off application. The one-off use case is designed to be used with `cron`, but other uses are welcome.

To run as a REST service, use `./gradlew bootRun`.
To run as a standalone, one-off application use `./gradlew run`
To create a standalone JAR, use `./gradlew bootJar`

## Configuration
The application can be configured using a JSON file. Create a file following the schema outlined in `sample-config.json`. The one-off use case will make use of the values defined in this file.

Note: A sample cron entry that might be helpful for this application, assuming that your server is running on Pacific time, would be `2 9 * * * java -jar <PATH_TO_JAR>`. This runs the program every day at 9:02AM pacific time, which is just 2 minutes after chrono.gg updates. This buffer time allows for any latency that may exist in the RSS feed refreshing.

The application takes an optional command-line argument, which is the relative file path of the config file. If no argument is provided, the program will fall back to `./config.json`.
