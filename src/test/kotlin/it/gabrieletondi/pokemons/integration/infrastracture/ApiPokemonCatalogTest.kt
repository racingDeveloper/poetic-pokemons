package it.gabrieletondi.pokemons.integration.infrastracture

import it.gabrieletondi.pokemons.domain.Description
import it.gabrieletondi.pokemons.domain.Name
import it.gabrieletondi.pokemons.domain.Pokemon
import it.gabrieletondi.pokemons.domain.PokemonCatalog
import it.gabrieletondi.pokemons.infrastracture.ApiPokemonCatalog
import it.gabrieletondi.pokemons.unit.domain.PokemonCatalogContractTest

class ApiPokemonCatalogTest : PokemonCatalogContractTest() {
    override fun anUnknownPokemonName(): Name = Name("donald")

    override fun aKnownPokemon(): Pokemon =
        Pokemon(
            Name("charizard"),
            Description("The flame inside its body burns hotter than 3,600 degrees Fahrenheit. When Charizard roars, that temperature climbs even higher.")
        )

    override fun aCatalog(): PokemonCatalog = ApiPokemonCatalog("https://pokeapi.co")
}