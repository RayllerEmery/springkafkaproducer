package br.com.springkafka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringkafkaApplication

fun main(args: Array<String>) {
	runApplication<SpringkafkaApplication>(*args)
}
