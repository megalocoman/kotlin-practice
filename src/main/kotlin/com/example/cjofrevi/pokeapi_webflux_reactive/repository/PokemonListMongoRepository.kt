package com.example.cjofrevi.pokeapi_webflux_reactive.repository

import com.example.cjofrevi.pokeapi_webflux_reactive.data.Pokemon
import com.example.cjofrevi.pokeapi_webflux_reactive.data.PokemonList
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface PokemonListMongoRepository : ReactiveMongoRepository<PokemonList, String> {

    fun findByName( name: String):  Mono<PokemonList>
}