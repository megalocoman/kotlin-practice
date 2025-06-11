package com.example.cjofrevi.pokeapi_webflux_reactive.service

import com.example.cjofrevi.pokeapi_webflux_reactive.data.Pokemon
import com.example.cjofrevi.pokeapi_webflux_reactive.data.PokemonList
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface PokemonService {

    fun getPokemonList(): Flux<PokemonList>
    fun getPokemonListFromDB(): Flux<PokemonList>
    fun getPokemonByName(name: String): Mono<Pokemon>

}