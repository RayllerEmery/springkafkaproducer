package br.com.springkafka.domain

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
class People (
    val name: String,
    val cpf: String,

    @OneToMany(mappedBy = "people")
    val books: List<Book>
        ) {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private lateinit var id: String
}