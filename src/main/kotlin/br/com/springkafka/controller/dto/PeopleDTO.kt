package br.com.springkafka.controller.dto

import br.com.springkafka.domain.Book

data class PeopleDTO (
    val name: String,
    val cpf: String,
    val books: List<String>
        )
