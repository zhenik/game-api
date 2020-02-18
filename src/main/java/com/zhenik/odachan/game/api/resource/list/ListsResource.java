package com.zhenik.odachan.game.api.resource.list;

import com.zhenik.odachan.game.api.domain.list.ListQuestions;
import com.zhenik.odachan.game.api.domain.user.User;
import com.zhenik.odachan.game.api.dto.commands.CreateListCommand;
import com.zhenik.odachan.game.api.dto.commands.UserSaveCommand;
import com.zhenik.odachan.game.api.service.ListService;
import com.zhenik.odachan.game.api.service.UserService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/lists")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class ListsResource {


  private ListService listService;

  public ListsResource(ListService listService) {
    this.listService = listService;
  }

  @POST
  public ListQuestions save(CreateListCommand createListCommand) {
    System.out.println("Here " + createListCommand);
    return listService.save(createListCommand);
  }

  //@GET
  //public List<ListQuestions> findAll() {
  //  return listService.findAll();
  //}

  @GET
  @QueryParam("email")
  public Response  findByEmail(@QueryParam("email") String email) {
    if (email == null || email.isEmpty()) {
      return Response.status(400).build();
    } else {
      return Response.ok(listService.findByEmail(email)).build();
    }
  }
}