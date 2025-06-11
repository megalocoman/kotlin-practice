package com.example.cjofrevi.pokeapi_webflux_reactive.repository

import com.example.cjofrevi.pokeapi_webflux_reactive.data.Pokemon
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface PokemonRepository : ReactiveMongoRepository<Pokemon, String> {
}