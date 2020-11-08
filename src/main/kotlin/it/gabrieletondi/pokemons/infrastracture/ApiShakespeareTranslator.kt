package it.gabrieletondi.pokemons.infrastracture

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.jackson.responseObject
import it.gabrieletondi.pokemons.domain.Description
import it.gabrieletondi.pokemons.domain.ShakespeareDescription
import it.gabrieletondi.pokemons.domain.ShakespeareTranslator
import it.gabrieletondi.pokemons.domain.TranslationException

class ApiShakespeareTranslator(baseUrl: String) : ShakespeareTranslator {
    private val fuel = FuelManager()

    init {
        fuel.basePath = baseUrl
    }

    override fun translate(description: Description): ShakespeareDescription {
        val request = fuel.get(
            "/translate/shakespeare.json", listOf(
                Pair("text", description.value)
            )
        )

        val (_, _, result) = request.responseObject<ShakespeareAPIResponse>()

        return result.fold(
            success = {
                ShakespeareDescription(it.contents.translated)
            },
            failure = {
                throw TranslationException(it.exception)
            }
        )
    }

    data class ShakespeareAPIResponse(val contents: Contents)

    data class Contents(val translated: String)
}