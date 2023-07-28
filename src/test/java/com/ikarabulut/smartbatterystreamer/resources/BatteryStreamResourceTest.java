package com.ikarabulut.smartbatterystreamer.resources;


import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DropwizardExtensionsSupport.class)
class BatteryStreamResourceTest {
    private static final ResourceExtension EXT = ResourceExtension      .builder()
            .addResource(new BatteryStreamResource())
            .build();

    @Test
    void add() {
    }
}
