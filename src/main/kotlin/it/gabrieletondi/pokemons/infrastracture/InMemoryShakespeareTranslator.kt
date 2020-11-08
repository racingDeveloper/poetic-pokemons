package it.gabrieletondi.pokemons.infrastracture

import it.gabrieletondi.pokemons.domain.*

class InMemoryShakespeareTranslator(private val translations: Map<Description, ShakespeareDescription>) : ShakespeareTranslator {
    override fun translate(description: Description): ShakespeareDescription {
        return translations[description] ?: throw TranslationException(UnknownInMemoryTranslation(description))
    }

    class UnknownInMemoryTranslation(description: Description): Exception("Unknown in memory translation for ${description.value}")
}