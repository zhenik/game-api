# Game API for gamefication prototype
Demo project using [Quarkus](https://quarkus.io/) and MongoDB with Panache

## Run dev
```bash
./mvnw quarkus:dev
```

## Packaging and running the application

The application is packageable using `./mvnw package`.

It produces the executable `code-with-quarkus-1.0.0-SNAPSHOT-runner.jar` file in `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.
The application is now runnable using `java -jar target/code-with-quarkus-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable
You can create a native executable using: `./mvnw package -Pnative`.
Or you can use Docker to build the native executable using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.
You can then execute your binary: `./target/code-with-quarkus-1.0.0-SNAPSHOT-runner`
If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image-guide .

## [entity] Question lists
```json
{
    "list_id": 1,
    "email": "nik12@gmail.com",
    "assigned_date": null,
    "delivered": null,
    "deadline": null,
    "questions": [
        {
            "id": 1,
            "text": "How old are you?",
            "answer": null,
            "comment": null,

            "score": 5
        }
    ],
    "state": null
}
```

POST /lists
```json
{
    "assignedToEmail": "nik12@gmail.com",
    "questions": [
        {
            "id": 1,
            "text": "How old are you?",
            "answer": null,
            "comment": null,
            "score": 5
        },
        {
            "id": 2,
            "text": "How old are you?",
            "answer": null,
            "comment": null,
            "score": 5
        }
    ]
}
```
