package it.gabrieletondi.pokemons.unit.infrastracture

import it.gabrieletondi.pokemons.domain.Description
import it.gabrieletondi.pokemons.domain.Name
import it.gabrieletondi.pokemons.domain.Pokemon
import it.gabrieletondi.pokemons.domain.PokemonCatalog
import it.gabrieletondi.pokemons.infrastracture.InMemoryPokemonCatalog
import it.gabrieletondi.pokemons.unit.domain.usecase.domain.PokemonCatalogContractTest

class InMemoryPokemonCatalogTest : PokemonCatalogContractTest() {
    private val knownPokemon = Pokemon(Name("a pokemon"), Description("the description"))
    
    private val catalog = InMemoryPokemonCatalog(
        listOf(
            knownPokemon,
            Pokemon(Name("another pokemon"), Description("the other description"))
        )
    )

    override fun anUnknownPokemonName(): Name = Name("unknown")

    override fun aKnownPokemon(): Pokemon = knownPokemon

    override fun aCatalog(): PokemonCatalog = catalog
}