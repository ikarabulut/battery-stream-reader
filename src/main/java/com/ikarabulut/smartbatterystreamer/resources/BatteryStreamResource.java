package com.ikarabulut.smartbatterystreamer.resources;

import com.google.common.collect.ImmutableMap;
import com.ikarabulut.smartbatterystreamer.api.DeviceDAO;
import com.smartbatterystreamer.avro.BatteryEvent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM;
import static org.apache.commons.compress.utils.IOUtils.toByteArray;

@Path("/")
public class BatteryStreamResource {
    protected final KafkaProducer producer;
    protected final String topic;
    protected final DeviceDAO dao;
    protected final String table;

    public BatteryStreamResource(KafkaProducer producer, String topic, DeviceDAO dao, String table) {
        this.producer = producer;
        this.topic = topic;
        this.dao = dao;
        this.table = table;
    }

    @POST
    @Path("/event/{uuid}")
    @Consumes({APPLICATION_OCTET_STREAM, APPLICATION_JSON})
    @Produces(APPLICATION_JSON)
    public Response send(@PathParam("uuid") String uuid, @Context HttpServletRequest request)
            throws IOException, ExecutionException, InterruptedException {
        ByteBuffer body = ByteBuffer.wrap(toByteArray(request.getInputStream()));
        BatteryEvent payload = new BatteryEvent(uuid, Instant.now().toEpochMilli(), body);

        ProducerRecord record = new ProducerRecord(topic, uuid, payload);
        Future<RecordMetadata> metadata = producer.send(record);

        return Response.ok().entity(serialize(metadata.get())).build();
    }

    protected Map<String, Object> serialize(RecordMetadata metadata) {
        return ImmutableMap.<String, Object>builder()
                .put("offset", metadata.offset())
                .put("partition", metadata.partition())
                .put("topic", metadata.topic())
                .put("timestamp", metadata.timestamp())
                .build();
    }
}

