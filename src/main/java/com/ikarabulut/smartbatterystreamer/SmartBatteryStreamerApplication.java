package com.ikarabulut.smartbatterystreamer;

import com.ikarabulut.smartbatterystreamer.resources.BatteryStreamResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

public class SmartBatteryStreamerApplication extends Application<SmartBatteryStreamerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new SmartBatteryStreamerApplication().run(args);
    }

    @Override
    public String getName() {
        return "SmartBatteryStreamer";
    }

    @Override
    public void initialize(final Bootstrap<SmartBatteryStreamerConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final SmartBatteryStreamerConfiguration configuration,
                    final Environment environment) {
        final BatteryStreamResource resource = new BatteryStreamResource();
        environment.jersey().register(resource);
    }

}
