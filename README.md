# poetic-pokemons

The application has been implemented in Kotlin, leveraging on hexagonal architecture.

The start-point is in file: ```Main.kt```

## Starting the application
You can start the application using the provided dockerfile and docker-compose file running ```make up```.

The application will be available at ```http://127.0.0.1:4567```

## Rebuilding and re-running the application
This can be done with command: ```make recreate```

## Stopping the application
You can stop the HTTP application with ```make halt```

## Running tests (unit + integration)
Test can be run with ```make test```

## Running e2e tests
First you need to run the application, then ```make test-e2e```

**Note**: this is very sub-optimal, in a real project with CI/CD pipeline the application would be already started and
the url would be injected from env.

## Assumptions
- because no specific pokemon game version was required, I am going to use the description from the latest version available in english
- the pokemon API and Shakespeare translations API are external services, for this reason the adapter implementations (ApiPokemonCatalog and ApiShakespeareTranslator) are tested in integration. 
If those were internal services using consumer driven contract tests would have been a better solution
- the translation API is rate limited, in case of any error the application will return a 503 error so that external service may retry later