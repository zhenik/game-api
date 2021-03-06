package com.zhenik.odachan.game.api.resource.list;

import com.zhenik.odachan.game.api.domain.list.ListQuestions;
import com.zhenik.odachan.game.api.dto.commands.CreateListCommand;
import com.zhenik.odachan.game.api.dto.commands.UpdateListCommand;
import com.zhenik.odachan.game.api.service.ListService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

  @GET
  @Path("/latest")
  @QueryParam("email")
  public Response findLatestUserList(@QueryParam("email") String email) {
    if (email == null || email.isEmpty()) {
      return Response.status(400).build();
    }
    else {
      ListQuestions latestUserList = listService.findLatestUserList(email);
      if (latestUserList != null) {
        return Response.ok(latestUserList).build();
      } else {
        return Response.status(404).build();
      }
    }
  }

  @GET
  @Path("/{id}")
  public Response findById(@PathParam("id") String id) {
    ListQuestions listQuestions = listService.findById(id);
    if (listQuestions != null) {
      return Response.ok(listQuestions).build();
    } else {
      return Response.status(404).build();
    }
  }

  @PUT
  @Path("/{id}")
  public Response updateById(@PathParam("id") String id, UpdateListCommand updateListCommand) {
    //System.out.println(updateListCommand);
    //System.out.println("["+id+" : "+updateListCommand.getId()+"]");
    if (!id.equals(updateListCommand.getId().toString())) {
      return Response.status(400).build();
    }
    listService.replaceById(id, updateListCommand);
    return Response.noContent().build();
  }
}
