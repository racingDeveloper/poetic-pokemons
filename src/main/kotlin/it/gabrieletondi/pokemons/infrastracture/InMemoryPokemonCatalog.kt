package it.gabrieletondi.pokemons.infrastracture

import it.gabrieletondi.pokemons.domain.Name
import it.gabrieletondi.pokemons.domain.Pokemon
import it.gabrieletondi.pokemons.domain.PokemonCatalog

class InMemoryPokemonCatalog(private val pokemon: List<Pokemon>) : PokemonCatalog {
    override fun find(name: Name): Pokemon {
        return pokemon.first { it.name == name }
    }
}