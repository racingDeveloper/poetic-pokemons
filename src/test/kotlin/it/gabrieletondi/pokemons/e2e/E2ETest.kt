package it.gabrieletondi.pokemons.e2e

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.HttpException
import com.github.kittinunf.fuel.jackson.responseObject
import org.junit.Ignore
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail

@Tag("e2e")
class E2ETest {
    private val fuel = FuelManager()

    private val baseUrl = "http://app:4567"

    @Test
    internal fun `ping pong`() {
        val request = fuel.get("$baseUrl/ping")

        val (_, response, result) = request.responseString()

        result.fold(
                success = { assertEquals(200, response.statusCode) },
                failure = { fail(it.exception) }
        )
    }

    @Test
    internal fun `known pokemon`() {
        val request = fuel.get("$baseUrl/pokemon/charizard")

        val (_, response, result) = request.responseObject<PokemonResponse>()

        result.fold(
                success = {
                    assertEquals(200, response.statusCode)
                    assertEquals(PokemonResponse(
                            "charizard",
                            "Charizard flies 'round the sky in search of powerful opponents. 't breathes fire of such most wondrous heat yond 't melts aught. However, 't nev'r turns its fiery breath on any opponent weaker than itself."),
                            result.component1())
                },
                failure = { fail(it.exception) }
        )
    }

    @Test
    internal fun `unknown pokemon`() {
        val request = fuel.get("$baseUrl/pokemon/donald")

        val (_, _, result) = request.responseObject<PokemonResponse>()

        result.fold(
            success = { fail("Expected 404 error") },
            failure = {
                assertEquals(404, it.response.statusCode)
            }
        )
    }

    private data class PokemonResponse(val name: String, val description: String)
}