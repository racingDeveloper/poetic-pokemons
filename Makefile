.PHONY: up
up:
	docker-compose up -d --remove-orphans
halt:
	docker-compose down -v
test:
	docker-compose run test ./gradlew clean build test