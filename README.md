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
  "id": "5e62aa68dfc6547295b20c96",
  "updatedAt": "2020-03-06T20:54:16.235",
  "createdAt": "2020-03-06T20:54:16.235",
  "assignedToEmail": "user@gmail.com",
  "assignedDate": "2020-03-06T20:54:16.234",
  "delivered": null,
  "deadline": null,
  "segments": [
    {
      "id": 1,
      "title": "Some title",
      "description": "some segment description",
      "questions": [
        {
          "id": 0,
          "text": "Bro?",
          "answer": "NONE",
          "comment": null,
          "score": 0
        },
        {
          "id": 1,
          "text": "Where is your home?",
          "answer": "NONE",
          "comment": null,
          "score": 0
        }
      ]
    },
    {
      "id": 2,
      "title": "Super title 2",
      "description": "some segment description",
      "questions": []
    }
  ],
  "state": "DELIVERED",
  "analytics": {
    "questions": 0,
    "score": 0,
    "feedback": 0.0,
    "group": {
      "NO": 0,
      "YES": 0,
      "IRRELEVANT": 0,
      "NONE": 0
    }
  }
}
```

## [dto] User analytics
```json
{
  "twoListsFeedback": {
    "last": {
      "questions": 0,
      "score": 0,
      "feedback": 0.0,
      "group": {
        "NO": 0,
        "YES": 0,
        "IRRELEVANT": 0,
        "NONE": 0
      }
    },
    "best": {
      "questions": 0,
      "score": 0,
      "feedback": 0.0,
      "group": {
        "NO": 0,
        "YES": 0,
        "IRRELEVANT": 0,
        "NONE": 0
      }
    }
  },
  "allListsFeedback": {
    "questions": 7,
    "score": 0,
    "feedback": 75.0,
    "group": {
      "IRRELEVANT": 2,
      "NO": 1,
      "YES": 3,
      "NONE": 1
    }
  }
}
```
