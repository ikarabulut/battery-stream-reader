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
