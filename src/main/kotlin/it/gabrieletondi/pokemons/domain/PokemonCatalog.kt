package it.gabrieletondi.pokemons.domain

interface PokemonCatalog {
    fun find(name: Name): Pokemon
}