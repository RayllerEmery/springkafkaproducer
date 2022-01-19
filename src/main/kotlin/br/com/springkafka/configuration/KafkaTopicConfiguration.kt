package br.com.springkafka.configuration

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaTopicConfiguration {

    @Value("\${topic.name}")
    private lateinit var topic: String

    @Bean
    fun create() = NewTopic(topic, 1, 1)
}