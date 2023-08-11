package com.ikarabulut.smartbatterystreamer.stream;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Environment;

public class App<T extends StreamConfiguration> extends Application<T> {

    @Override
    public void run(T configuration, Environment environment) throws Exception {
        // noop, nothing interesting needs to be setup. All the interesting work happens in StreamProcessor
    }
}
