package it.gabrieletondi.pokemons.domain

data class Pokemon(val name: Name, val description: Description)

data class Name(val value: String)

data class Description(val value: String)

data class ShakespearePokemon(val name: Name, val description: ShakespeareDescription)

data class ShakespeareDescription(val value: String)