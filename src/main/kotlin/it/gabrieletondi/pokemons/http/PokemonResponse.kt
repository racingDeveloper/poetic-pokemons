package it.gabrieletondi.pokemons.http

import it.gabrieletondi.pokemons.domain.ShakespearePokemon

data class PokemonResponse(val name: String, val description: String) {
    companion object {
        fun from(shakespearePokemon: ShakespearePokemon): PokemonResponse {
            return PokemonResponse(
                shakespearePokemon.name.value,
                shakespearePokemon.description.value
            )
        }
    }
}