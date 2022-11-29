package com.shared.prices.tracker.launcher

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "com.shared.prices.tracker"]
)
@EnableAutoConfiguration
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}