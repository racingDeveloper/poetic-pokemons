package it.gabrieletondi.pokemons.domain

interface ShakespeareTranslator {
    fun translate(description: Description): ShakespeareDescription
}

class TranslationException(innerException: Throwable): Exception("Something failed during translations", innerException)