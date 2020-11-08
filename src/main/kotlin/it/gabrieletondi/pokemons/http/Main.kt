package it.gabrieletondi.pokemons.http

import com.google.gson.Gson
import it.gabrieletondi.pokemons.domain.TranslationException
import it.gabrieletondi.pokemons.domain.UnknownPokemon
import it.gabrieletondi.pokemons.domain.usecase.ShakespearePokemonDescriptionUseCase
import it.gabrieletondi.pokemons.infrastracture.ApiPokemonCatalog
import it.gabrieletondi.pokemons.infrastracture.ApiShakespeareTranslator
import org.slf4j.LoggerFactory
import spark.Response
import spark.Spark.exception
import spark.kotlin.ignite

fun main() {
    val catalog = ApiPokemonCatalog("https://pokeapi.co")
    val translator = ApiShakespeareTranslator("https://api.funtranslations.com")

    val poeticPokemonDescriptionUseCase = ShakespearePokemonDescriptionUseCase(catalog, translator)

    val http = ignite()

    val logger = LoggerFactory.getLogger("web")

    http.before {
        logger.info("Received: ${this.request.requestMethod()} on: ${this.request.pathInfo()}")
    }

    http.finally {
        logger.info("Answering with status: ${this.response.status()} body: ${this.response.body()}")
    }

    http.get("/ping") {
        "pong"
    }

    http.get("/pokemon/:name") {
        val shakespearePokemon = poeticPokemonDescriptionUseCase.execute(request.params(":name"))
        val pokemonResponse = PokemonResponse.from(shakespearePokemon)
        jsonResponse(response, pokemonResponse)
    }

    exception(TranslationException::class.java) { _, _, response ->
        response.body("503 Translation Service Temporarily Unavailable")
        response.status(503)
    }

    exception(UnknownPokemon::class.java) { _, _, response ->
        response.body("404 Pokemon Not Found")
        response.status(404)
    }
}

private fun jsonResponse(response: Response, model: Any): String {
    response.type("application/json")
    return Gson().toJson(model)
}