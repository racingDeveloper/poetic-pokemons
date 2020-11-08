package it.gabrieletondi.pokemons.http

import com.google.gson.Gson
import it.gabrieletondi.pokemons.domain.Description
import it.gabrieletondi.pokemons.domain.Name
import it.gabrieletondi.pokemons.domain.Pokemon
import it.gabrieletondi.pokemons.domain.ShakespeareDescription
import it.gabrieletondi.pokemons.domain.usecase.ShakespearePokemonDescriptionUseCase
import it.gabrieletondi.pokemons.infrastracture.InMemoryPokemonCatalog
import it.gabrieletondi.pokemons.infrastracture.InMemoryShakespeareTranslator
import spark.Response
import spark.kotlin.ignite

fun main() {
    val catalog = InMemoryPokemonCatalog(
        listOf(
            Pokemon(
                Name("charizard"),
                Description("CHARIZARD flies around the sky in search of powerful opponents. It breathes fire of such great heat that it melts anything. However, it never turns its fiery breath on any opponent weaker than itself.")
            )
        )
    )

    val translator = InMemoryShakespeareTranslator(
        mapOf(
            Pair(
                Description("CHARIZARD flies around the sky in search of powerful opponents. It breathes fire of such great heat that it melts anything. However, it never turns its fiery breath on any opponent weaker than itself."),
                ShakespeareDescription("Charizard flies 'round the sky in search of powerful opponents. 't breathes fire of such most wondrous heat yond 't melts aught. However, 't nev'r turns its fiery breath on any opponent weaker than itself.")
            )
        )
    )

    val poeticPokemonDescriptionUseCase = ShakespearePokemonDescriptionUseCase(catalog, translator)

    val http = ignite()

    http.get("/ping") {
        "pong"
    }

    http.get("/pokemon/:name") {
        val shakespearePokemon = poeticPokemonDescriptionUseCase.execute(request.params(":name"))
        val pokemonResponse = PokemonResponse.from(shakespearePokemon)
        jsonResponse(response, pokemonResponse)
    }
}

private fun jsonResponse(response: Response, model: Any): String {
    response.type("application/json")
    return Gson().toJson(model)
}