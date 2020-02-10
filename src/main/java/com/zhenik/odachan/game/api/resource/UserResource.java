package com.zhenik.odachan.game.api.resource;

import com.zhenik.odachan.game.api.resource.projection.UserEmailRole;
import com.zhenik.odachan.game.api.domain.User;
import com.zhenik.odachan.game.api.dto.commands.UserSaveCommand;
import com.zhenik.odachan.game.api.service.UserService;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class UserResource {

  private UserService userService;

  public UserResource(UserService userService) {
    this.userService = userService;
  }

  @POST
  public User save(UserSaveCommand userSaveCommand) {
    return userService.save(userSaveCommand);
  }

  @GET
  public List<User> findAll() {
    return userService.findAll();
  }

  @GET
  @Path("/{id}")
  public User findOne(@PathParam("id") String id) {
    return null; // TODO
  }

  @DELETE
  @Path("/{id}")
  public void deleteOne(@PathParam("id") String id) {
    // TODO
  }

  @GET
  @Path("/{email}")
  public User findByEmail(@PathParam("email") String email) {
    return User.find("email", email).singleResult();
  }

  @POST
  @Path("/status")
  public Response status(UserEmailRole projection) {
    System.out.println(projection);

    Optional<UserEmailRole> userMaybe =
        User.find("email", projection.email).project(UserEmailRole.class).firstResultOptional();

    if (userMaybe.isPresent()) {
      return Response.ok().entity(userMaybe.get()).build();
    } else {
      return Response.status(404).build();
    }
  }
}
