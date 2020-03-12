package com.zhenik.odachan.game.api.resource.analytics;

import com.zhenik.odachan.game.api.dto.AnalyticsResult;
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
      final AnalyticsResult analytics = analyticsService.getUserAnalytics(email);
      return Response.ok(analytics).build();
    }
  }

  @GET
  @Path("top")
  public Response usersTop() {
    final List<EmailPercent> usersAnalytics = analyticsService.getUsersAnalytics();
    return Response.ok(usersAnalytics).build();
  }
}
