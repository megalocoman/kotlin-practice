package com.example.cjofrevi.pokeapi_webflux_reactive.service


import com.example.cjofrevi.pokeapi_webflux_reactive.data.Pokemon
import com.example.cjofrevi.pokeapi_webflux_reactive.data.PokemonList
import com.example.cjofrevi.pokeapi_webflux_reactive.model.ResultPokeList
import com.example.cjofrevi.pokeapi_webflux_reactive.repository.PokemonListMongoRepository
import com.example.cjofrevi.pokeapi_webflux_reactive.repository.PokemonRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.Optional


@Service
class PokemonServiceImpl(
    private val client: WebClient,
    private val repository: PokemonListMongoRepository,
    private val pokemonRepository: PokemonRepository
) : PokemonService {

    override fun getPokemonList(): Flux<PokemonList> {
        val path = "/pokemon"
        return callPokeApi(path, "1").bodyToFlux<ResultPokeList>()
            .flatMap { resultPokeList -> callPokeApi(path, resultPokeList.count.toString())
                .bodyToFlux<ResultPokeList>()}
            .flatMap { resultPokeList -> save(resultPokeList) }
    }

    override fun getPokemonListFromDB(): Flux<PokemonList> = repository.findAll()

    override fun getPokemonByName(name: String): Mono<Pokemon> =
        repository.findByName(name)
            .flatMap { result -> callPokeApi("/pokemon/$result.name", null)
                .bodyToMono<Pokemon>() }
            .flatMap { result ->
                pokemonRepository.save(result)
            }

    override fun fetchAndSaveAllPokemon(): Mono<String> =
        repository.findAll()
            .flatMap { resultList ->
                val path: String = "/pokemon/"+ resultList.name
                callPokeApi(path, null)
                    .bodyToMono<Pokemon>()
            }.onErrorContinue { error, response -> println("Pokemon $response had a problem $error")  }
            .flatMap { pokemon -> pokemonRepository.save(pokemon) }
            .then<String>(
                Mono.just<String>("fin")
            )

    private fun save(result: ResultPokeList): Flux<PokemonList> {

        val pokemonListEntity = mutableListOf<PokemonList>()
        result.results.forEach { item ->
            val pokemonList = PokemonList(ObjectId(), item.name, item.url)
            pokemonListEntity.add(pokemonList)
        }
        val fluxPokemonListEntity = Flux.fromIterable<PokemonList>(pokemonListEntity)
        return repository.insert<PokemonList>(fluxPokemonListEntity)
    }

    private fun callPokeApi(path: String, limitVal: String?): WebClient.ResponseSpec {

        val limitOptional: Optional<String> = Optional.ofNullable(limitVal)
        return client.get()
            .uri { uriBuilder ->
                uriBuilder
                    .path(path)
                    .queryParamIfPresent("limit", limitOptional)
                    .queryParam("offset", 0)
                    .build()
            }.retrieve()


    }
}
