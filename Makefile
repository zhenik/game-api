MVN := ./mvnw

.PHONY: all dev infra
all:

infra:
	docker-compose up -d
dev:
#	${MVN} clean compile quarkus:dev -Ddebug=true #-Dsuspend=true
	${MVN} compile quarkus:dev
