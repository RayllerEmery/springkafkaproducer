package br.com.springkafka.consumer

import br.com.springkafka.People
import br.com.springkafka.domain.Book
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional
import kotlin.system.exitProcess

@Component
class PeopleConsumer() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @PersistenceContext
    lateinit var manager: EntityManager

    @KafkaListener(topics = ["\${topic.name}"])
    @Transactional
    fun consumer(record: ConsumerRecord<String, People>, ack: Acknowledgment) {

        val people = record.value()

        logger.info(people.toString())

        var p = br.com.springkafka.domain.People()

        p.name = people.name.toString()
        p.cpf = people.cpf.toString()
        p.books = people.books.map {
            Book(it.toString())
        }

        manager.persist(p)

        ack.acknowledge()
    }
}