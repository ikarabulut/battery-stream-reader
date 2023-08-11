package com.ikarabulut.smartbatterystreamer.api;

import com.ikarabulut.smartbatterystreamer.resources.BatteryStreamResource;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.jdbi3.JdbiFactory;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.jdbi.v3.core.Jdbi;

import java.util.Properties;

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
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "device-db");

        var producer = createProducer(configuration);
        environment.lifecycle().manage(new CloseableManaged(producer));
        final BatteryStreamResource resource = new BatteryStreamResource(producer, configuration.getTopic(), jdbi.onDemand(DeviceDAO.class), configuration.getDeviceTable());
        environment.jersey().register(resource);
    }

    private KafkaProducer createProducer(SmartBatteryStreamerConfiguration conf) {
        Properties props = new Properties();

        props.put(ProducerConfig.ACKS_CONFIG, "1");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        // overrides
        props.putAll(conf.getKafka());
        return new KafkaProducer(props);
    }

}
