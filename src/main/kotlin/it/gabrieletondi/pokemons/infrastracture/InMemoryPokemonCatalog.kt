package it.gabrieletondi.pokemons.infrastracture

import it.gabrieletondi.pokemons.domain.Name
import it.gabrieletondi.pokemons.domain.Pokemon
import it.gabrieletondi.pokemons.domain.PokemonCatalog
import it.gabrieletondi.pokemons.domain.UnknownPokemon

class InMemoryPokemonCatalog(private val pokemon: List<Pokemon>) : PokemonCatalog {
    override fun find(name: Name): Pokemon {
        return this.pokemon.firstOrNull { it.name == name } ?: throw UnknownPokemon(name)
    }
}