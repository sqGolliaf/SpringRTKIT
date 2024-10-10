package com.sg.spring.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingsController {

    @PostMapping
    fun greetings(@RequestBody hello: String) = hello
}