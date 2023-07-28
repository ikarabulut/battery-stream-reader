package com.ikarabulut.smartbatterystreamer.resources;

import com.ikarabulut.smartbatterystreamer.db.BatteryEvent;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;


@Path("/event")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BatteryStreamResource {

    @POST
    public Response add(@NotNull @Valid BatteryEvent event) {
        return Response
                .status(Response.Status.CREATED)
                .entity(event)
                .build();
    }
}
