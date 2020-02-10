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

@Path("/users")
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
  @Path("/{email}")
  public User findByEmail(@PathParam("email") String email) {
    return userService.findByEmail(email);
  }

  @DELETE
  public Response deleteByEmail(UserEmailRole projection) {
    Long deleted = userService.deleteByEmail(projection.email);
    return Response.noContent().entity(deleted).build();
  }

  @POST
  @Path("/role")
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
