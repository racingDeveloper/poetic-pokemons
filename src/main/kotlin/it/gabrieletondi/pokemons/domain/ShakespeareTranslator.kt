package it.gabrieletondi.pokemons.domain

interface ShakespeareTranslator {
    fun translate(description: Description): ShakespeareDescription
}