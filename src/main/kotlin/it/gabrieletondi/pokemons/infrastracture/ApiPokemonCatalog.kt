package it.gabrieletondi.pokemons.infrastracture

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.jackson.responseObject
import it.gabrieletondi.pokemons.domain.*

class ApiPokemonCatalog(baseUrl: String) : PokemonCatalog {
    private val fuel = FuelManager()

    init {
        fuel.basePath = baseUrl
    }

    override fun find(name: Name): Pokemon {
        val request = fuel.get("/api/v2/pokemon-species/${name.value}/")
        val (_, _, result) = request.responseObject<PokemonAPIResponse>()

        return result.fold(
            success = {
                val (pokemonAPIResponse, fuelError) = result

                if (pokemonAPIResponse == null) {
                    throw fuelError!!.exception
                }

                val description = pokemonAPIResponse
                    .flavor_text_entries
                    .last { it.language.name == "en" }
                    .flavor_text

                Pokemon(name, Description(removeFormatting(description)))
            },
            failure = {
                if (it.response.statusCode == 404) {
                    throw UnknownPokemon(name)
                }

                throw it.exception
            }
        )


    }

    private fun removeFormatting(rawDescription: String): String {
        return rawDescription
            .replace("\n", " ")
            .replace("\u000c", " ")
    }

    data class PokemonAPIResponse(val flavor_text_entries: List<FlavorTextEntries>)

    data class FlavorTextEntries(val flavor_text: String, val language: Language, val version: Version)

    data class Language(val name: String)

    data class Version(val name: String)
}