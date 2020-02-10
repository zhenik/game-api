package com.zhenik.odachan.game.api.resource.user;

import com.zhenik.odachan.game.api.domain.user.User;
import com.zhenik.odachan.game.api.domain.user.UserRole;
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
        ", \"role\":"   +"\""+ role + "\"" +
        "}";
  }
}
