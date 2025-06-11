package com.example.cjofrevi.pokeapi_webflux_reactive.model


class ResultPokeList(val results: List<ApiNameResource>, count: Int, next: String?, previous: String?)
    : ListBase(count,  next, previous )
