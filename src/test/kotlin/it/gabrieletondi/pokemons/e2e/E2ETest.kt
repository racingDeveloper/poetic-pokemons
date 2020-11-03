package it.gabrieletondi.pokemons.e2e

import com.github.kittinunf.fuel.core.FuelManager
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

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
}