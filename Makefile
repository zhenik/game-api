MVN := ./mvnw

.PHONY: all
all:

.PHONY: infra
infra:
	docker-compose up -d

.PHONY: dev
dev:
#	${MVN} clean compile quarkus:dev -Ddebug=true #-Dsuspend=true
	${MVN} compile quarkus:dev
