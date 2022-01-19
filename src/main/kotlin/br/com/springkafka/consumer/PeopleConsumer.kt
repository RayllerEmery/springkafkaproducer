package br.com.springkafka.consumer

import br.com.springkafka.People
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class PeopleConsumer() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = ["\${topic.name}"])
    fun consumer(record: ConsumerRecord<String, People>, ack: Acknowledgment) {
        val people = record.value()

        logger.info(people.toString())
        ack.acknowledge()
    }
}