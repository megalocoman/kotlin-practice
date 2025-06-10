package com.example.cjofrevi.pokeapi_webflux_reactive.data

import com.example.cjofrevi.pokeapi_webflux_reactive.model.PokeList
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("Pokemon_List")
class PokemonList(@Id val id: ObjectId = ObjectId(), name: String, url: String) : PokeList(name, url)
