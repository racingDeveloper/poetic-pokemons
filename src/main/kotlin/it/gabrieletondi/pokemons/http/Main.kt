package it.gabrieletondi.pokemons.http

import com.google.gson.Gson
import spark.Response
import spark.kotlin.ignite

fun main() {
    val http = ignite()

    http.get("/ping") {
        "pong"
    }

    http.get("/pokemon/:name") {
        val name = request.params(":name")
        val description = "Charizard flies 'round the sky in search of powerful opponents. 't breathes fire of such most wondrous heat yond 't melts aught. However, 't nev'r turns its fiery breath on any opponent weaker than itself."
        val pokemonResponse = PokemonResponse(name, description)
        jsonResponse(response, pokemonResponse)
    }
}

private fun jsonResponse(response: Response, model: Any): String {
    response.type("application/json")
    return Gson().toJson(model)
}