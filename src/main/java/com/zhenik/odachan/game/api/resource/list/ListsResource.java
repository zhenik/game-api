package com.zhenik.odachan.game.api.resource.list;

import com.zhenik.odachan.game.api.domain.list.ListQuestions;
import com.zhenik.odachan.game.api.dto.commands.CreateListCommand;
import com.zhenik.odachan.game.api.service.ListService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    return listService.save(createListCommand);
  }

  @GET
  @QueryParam("email")
  public Response findByEmail(@QueryParam("email") String email) {
    if (email == null || email.isEmpty()) {
      return Response.status(400).build();
    } else {
      return Response.ok(listService.findByEmail(email)).build();
    }
  }
}
