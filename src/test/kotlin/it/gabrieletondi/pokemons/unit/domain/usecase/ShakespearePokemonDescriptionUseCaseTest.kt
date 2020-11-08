package it.gabrieletondi.pokemons.unit.domain.usecase

import it.gabrieletondi.pokemons.domain.*
import it.gabrieletondi.pokemons.domain.usecase.ShakespearePokemonDescriptionUseCase
import it.gabrieletondi.pokemons.infrastracture.InMemoryShakespeareTranslator
import it.gabrieletondi.pokemons.infrastracture.InMemoryPokemonCatalog
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class ShakespearePokemonDescriptionUseCaseTest {
    private val aPokemonName = Name("a pokemon")
    private val aDescription = Description("a description")

    private val catalog = InMemoryPokemonCatalog(
        listOf(
            Pokemon(aPokemonName, aDescription),
            Pokemon(Name("another pokemon"), Description("another description")),
        )
    )

    private val aShakespeareDescription = ShakespeareDescription("the shakespeare description")

    private val translator = InMemoryShakespeareTranslator(mapOf(
        Pair(aDescription, aShakespeareDescription)
    ))

    private val useCase = ShakespearePokemonDescriptionUseCase(catalog, translator)

    @Test
    internal fun `found pokemon`() {
        val shakespearePokemon = useCase.execute("a pokemon")

        assertEquals(
            ShakespearePokemon(aPokemonName, aShakespeareDescription),
            shakespearePokemon
        )
    }

    @Test
    internal fun `unknown pokemon`() {
        val exception = assertThrows<UnknownPokemon> {
            useCase.execute("unknown")
        }

        assertEquals("Unknown pokemon with name unknown", exception.message)
    }
}