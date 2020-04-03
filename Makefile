MVN := ./mvnw

.PHONY: all backend-dev db dev db-seed-user db-seed-list compile-native build-image
all: dev

db:
	docker-compose up -d mongo
backend-dev:
	${MVN} compile quarkus:dev
dev: db backend-dev

# seeding database
db-seed-user:
	docker run -t -i --rm \
		--network=game-api_backend \
		-v ${PWD}/db/seed/:/tmp/db/seed/ \
		mongo:4.2 \
		mongoimport \
		-h mongo \
		-d app \
		-c user \
		--authenticationDatabase admin \
		--type=json \
		--file=/tmp/db/seed/user.json \
		--jsonArray \
		-u root -p example
db-seed-list:
	docker run -t -i --rm \
		--network=game-api_backend \
		-v ${PWD}/db/seed/:/tmp/db/seed/ \
		mongo:4.2 \
		mongoimport \
		-h mongo \
		-d app \
		-c user \
		--authenticationDatabase admin \
		--type=json \
		--file=/tmp/db/seed/list.json \
		--jsonArray \
		-u root -p example

# compile inside container for Linux OS
compile-native:
	./mvnw package -Pnative -Dquarkus.native.container-build=true \
      -Dquarkus.native.container-runtime=docker
build-image:
	docker build -f src/docker/Dockerfile.native -t zhenik/ocn-backend:1.0 .
