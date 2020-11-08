package it.gabrieletondi.pokemons.integration.infrastracture

import it.gabrieletondi.pokemons.domain.Description
import it.gabrieletondi.pokemons.domain.ShakespeareDescription
import it.gabrieletondi.pokemons.domain.ShakespeareTranslator
import it.gabrieletondi.pokemons.infrastracture.ApiShakespeareTranslator
import it.gabrieletondi.pokemons.unit.domain.ShakespeareTranslatorContractTest

class ApiShakespeareTranslatorTest : ShakespeareTranslatorContractTest() {
    override fun theTranslatedDescription(): ShakespeareDescription =
        ShakespeareDescription("Thee did giveth mr. Tim a hearty meal,  but unfortunately what he did doth englut did maketh him kicketh the bucket.")

    override fun aTranslatableDescription(): Description =
        Description("You gave Mr. Tim a hearty meal, but unfortunately what he ate made him die.")

    override fun aTranslator(): ShakespeareTranslator = ApiShakespeareTranslator("https://api.funtranslations.com")
}