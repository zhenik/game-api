MVN := ./mvnw

.PHONY: all backend-dev init-db db dev-mode
all: dev-mode

init-db:
	docker-compose up -d mongo-seed
db:
	docker-compose up -d mongo
backend-dev:
	${MVN} compile quarkus:dev
dev-mode: db backend-dev
