package br.com.springkafka.producer


import br.com.springkafka.Car
import br.com.springkafka.People
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Slf4j
@Component
class CarProducer(
    @Autowired
    val kafkaTemplate: KafkaTemplate<String, Car>,
    @Value("\${topic.name}")
    val topicName: String
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun sendMessage(car: Car) {
        kafkaTemplate.send(topicName, car.id as String, car).addCallback(
            {logger.info("Mensagem enviada com sucesso!")},
            {logger.info("Falha ao enviar mensagem!")}
        )
    }
}