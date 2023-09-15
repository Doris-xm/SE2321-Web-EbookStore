package com.example.ebook_back.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

//    @Bean
//    public NewTopic topic() {
//        return TopicBuilder.name("orders")
//                .partitions(10)
//                .replicas(1)
//                .build();
//    }
    // zookeeper-server-start  /opt/homebrew/Cellar/kafka/3.5.1/libexec/config/zookeeper.properties
    // kafka-server-start /opt/homebrew/etc/kafka/server.properties
    // kafka-topics --create --topic orders --bootstrap-server localhost:9092
    // kafka-topics --list --bootstrap-server localhost:9092


}
