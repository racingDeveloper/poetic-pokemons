.PHONY: up
up:
	docker-compose up -d --remove-orphans
halt:
	docker-compose down -v
test:
	docker-compose run --rm test ./gradlew clean build test
test-e2e:
	docker-compose run --rm test ./gradlew clean build e2eTest --info
recreate:
	docker-compose up -d --force-recreate --build --remove-orphans