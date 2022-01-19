package br.com.springkafka.controller

import br.com.springkafka.People
import br.com.springkafka.controller.dto.PeopleDTO
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
@RequestMapping("peoples")
class PeopleController (
    @Autowired
    val peopleProducer: PeopleProducer
        ) {

    @PostMapping
    fun sendMessage(@RequestBody peopleDto: PeopleDTO): ResponseEntity<Any> {

        val id = UUID.randomUUID().toString()
        val message =
            People.newBuilder()
                .setId(id)
                .setName(peopleDto.name)
                .setCpf(peopleDto.cpf)
                .setBooks(peopleDto.books.stream().map {
                    p -> p as CharSequence
                }.collect(Collectors.toList()))
                .build()
        peopleProducer.sendMessage(message)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}