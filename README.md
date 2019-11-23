# Chrono.gg API

A simple application that wraps the Deal-of-the-Day RSS system found on chrono.gg into a REST API.

## Technologies
- Kotlin
- Spring Boot
- RSS

## Usage
The application can be run either as a Spring Boot REST service, or as a simple on-off application. The one-off use case is designed to be used with `cron`, but other uses are welcome.

To run as a REST service, use `./gradlew bootRun`.
To run as a standalone, one-off application use `./gradlew run`

## Configuration
The application can be configured using a JSON file. Create a file following the schema outlined in `sample-config.json`. The one-off use case will make use of the values defined in this file.

Note: A sample cron entry that might be helpful for this application, assuming that your server is running on Pacific time, would be `2 9 * * * java -jar <PATH_TO_JAR>`. This runs the program every day at 9:02AM pacific time, which is just 2 minutes after chrono.gg updates. This buffer time allows for any latency that may exist in the RSS feed refreshing.
