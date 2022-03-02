package br.com.springkafka.controller

import br.com.springkafka.Car
import br.com.springkafka.People
import br.com.springkafka.controller.dto.CarDTO
import br.com.springkafka.controller.dto.PeopleDTO
import br.com.springkafka.producer.CarProducer
import br.com.springkafka.producer.PeopleProducer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.stream.Collectors

@RestController
@RequestMapping("cars")
class CarController (
    @Autowired
    val carProducer: CarProducer
        ) {

    @PostMapping
    fun sendMessage(@RequestBody carDTO: CarDTO): ResponseEntity<Any> {

        val id = UUID.randomUUID().toString()
        val message =
            Car.newBuilder()
                .setId(id)
                .setName(carDTO.name)
                .setCpf(carDTO.cpf)
                .setBrand(carDTO.brand)
                .build()
        carProducer.sendMessage(message)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}