package com.example.cjofrevi.pokeapi_webflux_reactive.repository

import com.example.cjofrevi.pokeapi_webflux_reactive.data.PokemonList
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface PokemonListMongoRepository : ReactiveMongoRepository<PokemonList, String> {

}