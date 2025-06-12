package com.example.cjofrevi.pokeapi_webflux_reactive.data

import com.example.cjofrevi.pokeapi_webflux_reactive.model.ApiNameResource
import com.google.gson.annotations.SerializedName
import org.springframework.data.mongodb.core.mapping.Document

@Document("Pokemon")
data class Pokemon(
    val abilities: List<Ability>,
    val base_experience: Int,
    val cries: Cries?,
    val forms: List<ApiNameResource>,
    val game_indices: List<Object>?,
    val height: Int,
    val held_items: List<Object>?,
    val id: Int,
    val isDefault: Boolean,
    val location_area_encounters: String?,
    val moves: List<Object>,
    val name: String,
    val order: Int,
    val past_abilities: List<PastAbilities>?,
    val past_types: List<PastTypes>?,
    val species: ApiNameResource,
    val sprites: Object,
    val stats: List<Object>,
    val types: List<Type>,
    val weight: Int
)

open class PastTypes(
    val generation: ApiNameResource,
    val types: List<Type>
)

open class Type(
    val slot: Int,
    val type: ApiNameResource
)

open class PastAbilities(
    val abilities: List<Ability>?,
    val generation: ApiNameResource
)

open class Cries(val latest: String?, val legacy: String?)

open class Ability(
    val ability: ApiNameResource?,
    @SerializedName("is_hidden")
    val isHidden: Boolean?,
    val slot: Int?
)
