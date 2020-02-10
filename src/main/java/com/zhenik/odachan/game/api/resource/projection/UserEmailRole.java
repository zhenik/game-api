package com.zhenik.odachan.game.api.resource.projection;

import com.zhenik.odachan.game.api.domain.User;
import com.zhenik.odachan.game.api.domain.enums.UserRole;
import io.quarkus.mongodb.panache.ProjectionFor;
/**
import io.quarkus.mongodb.panache.ProjectionFor;
@ProjectionFor(Person.class)
public class PersonName {
  public String name;
}
PanacheQuery<PersonName> shortQuery = Person.find("status ", Status.Alive).project(PersonName.class);
*/

@ProjectionFor(User.class)
public class UserEmailRole {
  public String email;
  public UserRole role;

  @Override public String toString() {
    return "{" +
        "\"email\":"    +"\""+ email + "\"" +
        ", \"role:\""   +"\""+ role + "\"" +
        "}";
  }
}
