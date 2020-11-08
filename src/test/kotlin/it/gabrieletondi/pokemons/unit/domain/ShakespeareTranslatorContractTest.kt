package it.gabrieletondi.pokemons.unit.domain

import it.gabrieletondi.pokemons.domain.Description
import it.gabrieletondi.pokemons.domain.ShakespeareDescription
import it.gabrieletondi.pokemons.domain.ShakespeareTranslator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

abstract class ShakespeareTranslatorContractTest {
    @Test
    internal fun `translatable text`() {
        val translated = aTranslator().translate(aTranslatableDescription())
        
        assertEquals(theTranslatedDescription(), translated)
    }

    abstract fun theTranslatedDescription(): ShakespeareDescription

    abstract fun aTranslatableDescription(): Description

    abstract fun aTranslator(): ShakespeareTranslator
}