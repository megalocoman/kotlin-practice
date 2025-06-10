package com.example.cjofrevi.pokeapi_webflux_reactive.controller

import com.example.cjofrevi.pokeapi_webflux_reactive.service.PokemonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class ExampleController(private val service: PokemonService) {

    @GetMapping
    fun getListPokemon() = service.getPokemonList()

}