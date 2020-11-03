# poetic-pokemons

## Starting the application
You can start the application using the provided dockerfile and docker-compose file running ```make up```.

The application will be available at ```http://127.0.0.1:4567```

## Stopping the application
You can stop the HTTP application with ```make halt```

## Running tests (unit + integration)
Test can be run with ```make test```

## Running e2e tests
First you need to run the application, then ```make test-e2e```

**Note:**: this is very sub-optimal, in a real project with CI/CD pipeline the application would be already started and
the url would be injected from env.