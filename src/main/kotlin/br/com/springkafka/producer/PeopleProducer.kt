package br.com.springkafka.producer


import br.com.springkafka.People
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Slf4j
@Component
class PeopleProducer(
    @Autowired
    val kafkaTemplate: KafkaTemplate<String, People>,
    @Value("\${topic.name}")
    val topicName: String
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun sendMessage(people: People) {
        kafkaTemplate.send(topicName, people.id as String, people).addCallback(
            {logger.info("Mensagem enviada com sucesso!")},
            {logger.info("Falha ao enviar mensagem!")}
        )
    }
}