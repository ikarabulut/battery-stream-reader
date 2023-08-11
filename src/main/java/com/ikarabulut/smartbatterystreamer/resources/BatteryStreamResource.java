package com.ikarabulut.smartbatterystreamer.resources;

import com.smartbatterystreamer.avro.BatteryEvent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.Instant;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM;
import static org.apache.commons.compress.utils.IOUtils.toByteArray;

@Path("/")
public class BatteryStreamResource {
    protected final KafkaProducer producer;
    protected final String topic;
    public BatteryStreamResource(KafkaProducer producer, String topic) {
        this.producer = producer;
        this.topic = topic;
    }

    @POST
    @Path("/event/{uuid}")
    @Consumes({APPLICATION_OCTET_STREAM, APPLICATION_JSON})
    @Produces(APPLICATION_JSON)
    public Response send(@PathParam("uuid") String uuid, @Context HttpServletRequest request)
            throws IOException {
        ByteBuffer body = ByteBuffer.wrap(toByteArray(request.getInputStream()));
        BatteryEvent payload = new BatteryEvent(uuid, Instant.now().toEpochMilli(), body);

        return Response.ok().entity(payload).build();
    }
}

