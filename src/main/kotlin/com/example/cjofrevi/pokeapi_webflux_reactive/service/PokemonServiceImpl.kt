package com.example.cjofrevi.pokeapi_webflux_reactive.service


import com.example.cjofrevi.pokeapi_webflux_reactive.data.PokemonList
import com.example.cjofrevi.pokeapi_webflux_reactive.model.ResultPokeList
import com.example.cjofrevi.pokeapi_webflux_reactive.repository.PokemonListMongoRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class PokemonServiceImpl(
    private val client: WebClient,
    private val repository: PokemonListMongoRepository
) : PokemonService {
    
    override fun getPokemonList(): Flux<PokemonList> {
        return getPokemonList(1)
            .flatMap { resultPokeList -> getPokemonList(resultPokeList.count).log() }
            .flatMap { resultPokeList -> save(resultPokeList).log() }
    }

    private fun save(result: ResultPokeList): Flux<PokemonList> {

        val pokemonListEntity = mutableListOf<PokemonList>()
        result.results.forEach { item ->
            val pokemonList = PokemonList(ObjectId(), item.name, item.url)
            pokemonListEntity.add(pokemonList)
        }
        val fluxPokemonListEntity = Flux.fromIterable<PokemonList>(pokemonListEntity)
        return repository.insert<PokemonList>(fluxPokemonListEntity)
    }

    private fun getPokemonListJson(limit: Int): Mono<String> =
        client.get()
            .uri { uriBuilder ->
                uriBuilder
                    .path("/pokemon")
                    .queryParam("limit", limit)
                    .queryParam("offset", 0)
                    .build()
            }
            .retrieve()
            .bodyToMono<String>()

    private fun getPokemonList(limit: Int): Flux<ResultPokeList> =
        client.get()
            .uri { uriBuilder ->
                uriBuilder
                    .path("/pokemon")
                    .queryParam("limit", limit)
                    .queryParam("offset", 0)
                    .build()
            }
            .retrieve()
            .bodyToFlux<ResultPokeList>()
}
