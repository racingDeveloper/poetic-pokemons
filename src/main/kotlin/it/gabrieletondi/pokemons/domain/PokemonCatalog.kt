package it.gabrieletondi.pokemons.domain

interface PokemonCatalog {
    fun find(name: Name): Pokemon
}

class UnknownPokemon(name: Name) : Exception("Unknown pokemon with name ${name.value}")