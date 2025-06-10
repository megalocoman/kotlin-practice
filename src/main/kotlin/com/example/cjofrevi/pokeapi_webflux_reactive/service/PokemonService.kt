package com.example.cjofrevi.pokeapi_webflux_reactive.service

import com.example.cjofrevi.pokeapi_webflux_reactive.data.PokemonList
import reactor.core.publisher.Flux

interface PokemonService {

    fun getPokemonList(): Flux<PokemonList>

}