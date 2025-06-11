package com.example.cjofrevi.pokeapi_webflux_reactive.data

import com.example.cjofrevi.pokeapi_webflux_reactive.model.ApiNameResource
import com.google.gson.annotations.SerializedName
import org.springframework.data.mongodb.core.mapping.Document

@Document("Pokemon")
data class Pokemon(
    val abilities: List<Ability>,
    @SerializedName("base_experience")
    val baseExperience: Int,
    val cries: Cries,
    val forms: List<ApiNameResource>,
    @SerializedName("game_indices")
    val gameIndices: List<GameIndex>?,
    val height: Int,
    @SerializedName("held_items")
    val heldItems: List<HeldItems>?,
    val id: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String?,
    val moves: List<Move>,
    val names: String?,
    val order: Int,
    @SerializedName("past_abilities")
    val pastAbilities: List<PastAbilities>?,
    @SerializedName("past_types")
    val pastTypes: List<PastTypes>?,
    val species: ApiNameResource,
    val sprites: Sprite,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)

open class Stat(
    @SerializedName("base_stat")
    val baseStat: Int,
    val effort: Int,
    val stat: ApiNameResource
)

open class Sprite(
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_female") val backFemale: String?,
    @SerializedName("back_shiny_female") val backShinyFemale: String?,
    @SerializedName("back_shiny") val backShiny: String?,
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?
//    val other: Any?,
//    val versions: Any?
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
    val abilities: List<Ability>,
    val generation: ApiNameResource
)

open class Move(
    val move: ApiNameResource,
    @SerializedName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>?
)

open class VersionGroupDetail(
    @SerializedName("level_learned_at")
    val levelLearnedAt: Int,
    @SerializedName("move_learn_method")
    val moveLearnMethod: ApiNameResource,
    val order: Int?,
    @SerializedName("version_group")
    val versionGroup: ApiNameResource
)

open class HeldItems(

    val item: ApiNameResource,
    @SerializedName("version_details")
    val versionDetails: List<VersionDetail>
)

open class VersionDetail(val rarity: Int, val version: ApiNameResource)

open class GameIndex(
    @SerializedName("game_index")
    val gameIndex: Int,
    val version: ApiNameResource
)


open class Cries(val latest: String, val legacy: String)

open class Ability(
    val ability: ApiNameResource,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    val slot: Int
)
