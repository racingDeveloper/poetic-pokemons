package it.gabrieletondi.pokemons.domain.usecase

import it.gabrieletondi.pokemons.domain.Name
import it.gabrieletondi.pokemons.domain.PokemonCatalog
import it.gabrieletondi.pokemons.domain.ShakespearePokemon
import it.gabrieletondi.pokemons.domain.ShakespeareTranslator

class ShakespearePokemonDescriptionUseCase(private val catalog: PokemonCatalog, private val translator: ShakespeareTranslator) {
    fun execute(pokemonName: String): ShakespearePokemon {
        val (name, description) = catalog.find(Name(pokemonName))
        val shakespeareDescription = translator.translate(description)
        return ShakespearePokemon(name, shakespeareDescription)
    }
}