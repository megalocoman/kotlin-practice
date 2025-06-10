package com.example.cjofrevi.pokeapi_webflux_reactive.client

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class ConfigWebClient {

    @Bean
    fun webclient(builder: WebClient.Builder): WebClient =
        builder
            .baseUrl("https://pokeapi.co/api/v2")
            .build()
}
