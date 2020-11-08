package it.gabrieletondi.pokemons.unit.infrastracture

import it.gabrieletondi.pokemons.domain.Description
import it.gabrieletondi.pokemons.domain.ShakespeareDescription
import it.gabrieletondi.pokemons.domain.ShakespeareTranslator
import it.gabrieletondi.pokemons.domain.TranslationException
import it.gabrieletondi.pokemons.infrastracture.InMemoryShakespeareTranslator
import it.gabrieletondi.pokemons.unit.domain.ShakespeareTranslatorContractTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InMemoryShakespeareTranslatorTest : ShakespeareTranslatorContractTest() {
    private val translatableDescription = Description("translatable")
    private val translatedDescription = ShakespeareDescription("translated")

    private val translator = InMemoryShakespeareTranslator(mapOf(
        Pair(translatableDescription, translatedDescription),
        Pair(Description("another translatable"), ShakespeareDescription("translated again"))
    ))

    override fun theTranslatedDescription(): ShakespeareDescription = translatedDescription

    override fun aTranslatableDescription(): Description = translatableDescription

    override fun aTranslator(): ShakespeareTranslator = translator

    @Test
    internal fun `an unknown translation`() {
        assertThrows<TranslationException> {
            translator.translate(Description("you don't know how to translate this"))
        }
    }
}