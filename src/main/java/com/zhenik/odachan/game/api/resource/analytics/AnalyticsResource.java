package com.zhenik.odachan.game.api.resource.analytics;

import com.zhenik.odachan.game.api.dto.UserAnalyticsDto;
import com.zhenik.odachan.game.api.dto.EmailPercent;
import com.zhenik.odachan.game.api.service.AnalyticsService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// todo: extract to separate module
@Path("/analytics")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class AnalyticsResource {

  private AnalyticsService analyticsService;

  public AnalyticsResource(AnalyticsService analyticsService) {
    this.analyticsService = analyticsService;
  }

  @GET
  @QueryParam("email")
  public Response userAnalytics(@QueryParam("email") String email) {
    if (email == null || email.isEmpty()) {
      return Response.status(400).build();
    } else {
      UserAnalyticsDto analytics = analyticsService.getUserAnalytics(email);
      if (analytics != null) {
        return Response.ok(analytics).build();
      } else {
        return Response.status(404).build();
      }
    }
  }

  @GET
  @Path("top")
  public Response usersTop() {
    List<EmailPercent> usersAnalytics = analyticsService.getUsersAnalytics();
    if (usersAnalytics != null) {
      return Response.ok(usersAnalytics).build();
    } else {
      return Response.status(404).build();
    }

  }
}
