package com.example.cjofrevi.pokeapi_webflux_reactive.controller

import com.example.cjofrevi.pokeapi_webflux_reactive.service.PokemonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class ExampleController(private val service: PokemonService) {

    @GetMapping
    fun getListPokemon() = service.getPokemonList()

    @GetMapping("getList")
    fun getListPokemonFromDB() =service.getPokemonListFromDB()

    @GetMapping("getPokemonByName/{name}")
    fun getPokemonByName(@PathVariable name :String ) = service.getPokemonByName(name)

}