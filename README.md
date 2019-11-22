# Chrono.gg API

A simple application that wraps the Deal-of-the-Day RSS system found on chrono.gg into a REST API.

## Technologies
- Kotlin
- Spring Boot
- RSS

## Usage
The application can be run either as a Spring Boot REST service, or as a simple on-off application. The one-off use case is designed to be used with `cron`, but other uses are welcome.

To run as a REST service, use `./gradlew bootRun`.
To run as a standalone, one-off application use `./gradlew runCron`

## Configuration
The application can be configured using a JSON file. Create a file following the schema outlined in `sample-config.json`. The one-off use case will make use of the values defined in this file.

