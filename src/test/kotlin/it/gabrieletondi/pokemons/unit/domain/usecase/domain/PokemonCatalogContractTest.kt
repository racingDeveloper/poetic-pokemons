package it.gabrieletondi.pokemons.unit.domain.usecase.domain

import it.gabrieletondi.pokemons.domain.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

abstract class PokemonCatalogContractTest {
    @Test
    internal fun `known pokemon`() {
        val aKnownPokemon = aKnownPokemon()
        val foundPokemon = aCatalog().find(aKnownPokemon.name)

        assertEquals(aKnownPokemon, foundPokemon)
    }

    @Test
    internal fun `an unknown pokemon`() {
        assertThrows<UnknownPokemon> {
            aCatalog().find(anUnknownPokemonName())
        }
    }

    abstract fun anUnknownPokemonName(): Name

    abstract fun aKnownPokemon(): Pokemon

    abstract fun aCatalog(): PokemonCatalog
}