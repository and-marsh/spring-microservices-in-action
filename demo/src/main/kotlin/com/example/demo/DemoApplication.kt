package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@SpringBootApplication
@RestController
@RequestMapping(value = ["hello"])
class DemoApplication {

    @GetMapping(value = ["/{firstName}"])
    fun helloGET(
        @PathVariable("firstName")
        firstName: String,
        @RequestParam("lastName")
        lastName: String
    ): String {
        return "{\"message\": \"Hello $firstName $lastName\"}"
    }
}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
