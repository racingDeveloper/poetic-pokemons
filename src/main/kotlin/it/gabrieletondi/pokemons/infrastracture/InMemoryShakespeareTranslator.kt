package it.gabrieletondi.pokemons.infrastracture

import it.gabrieletondi.pokemons.domain.Description
import it.gabrieletondi.pokemons.domain.ShakespeareDescription
import it.gabrieletondi.pokemons.domain.ShakespeareTranslator

class InMemoryShakespeareTranslator(private val translations: Map<Description, ShakespeareDescription>) : ShakespeareTranslator {
    override fun translate(description: Description): ShakespeareDescription {
        return translations.getOrDefault(description, ShakespeareDescription("cannot translate description"))
    }
}