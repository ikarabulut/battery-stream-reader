# SmartBatteryStreamer

This project is my implementation of the manning live-project [Managing a distrubuted electrical grid in real time with Kafka](https://liveproject.manning.com/module/153_1_2/managing-a-distributed-electrical-grid-in-real-time-with-kafka/introduction/about-this-liveproject?)

## Techniques employed
This project is designed to build a system from these first principles: We will start with a straightforward approach to solving a focused subset of the problem and then we will grow our solution to meet the demands as they arrive. Much like in the real world, we will spend some time redoing some of the design as the requirements evolve and require increasingly more advanced techniques.

- Partitioning data to avoid skew
- Using Avro to define and evolve schema
- Design fault-tolerant stream processing
- Extract IoT fleet-wide metadata
- Tolerate real-world IoT edge cases, like malformed or excessively large events
- Provide real-time, geo-based information on the fleet
- Handle out-of-order events
- Build multiuse APIs for real-time IoT insights

## Run Locally
This project utilized `docker-compose` to run Kafka, Zookeeper, Postgres, and Confluent Schema registry. For a sample event generator please see https://github.com/jyates/manning-energy-resources/tree/master/event-generators and follow the instructions to get this project on your machine as well.
1. Once the project is cloned navigate to the root directory
2. Run `mvn clean package`
3. Run `docker-compose up` to run the docker container
4. Run `java -jar target/smart-battery-streamer-1.0-SNAPSHOT.jar server config.yml` this will run the dropwizard server on port 8080 and use the config.yml file located in the root dir
5. Run the event generator application following the instructions from the link above
