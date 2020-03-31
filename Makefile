MVN := ./mvnw

.PHONY: all backend-dev db dev-mode db-seed-user db-seed-list
all: dev-mode

db:
	docker-compose up -d mongo
backend-dev:
	${MVN} compile quarkus:dev
dev-mode: db backend-dev

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
