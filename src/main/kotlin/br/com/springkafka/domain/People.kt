package br.com.springkafka.domain

import com.fasterxml.jackson.databind.ser.AnyGetterWriter
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
class People{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    val id: String? = ""
    var name: String? = null
    var cpf: String? = null

    @ManyToMany(cascade = [CascadeType.ALL])
    var books: List<Book>? = null

    constructor()

    constructor(name: String, cpf: String, books: List<Book>) {
        this.name = name
        this.cpf = cpf
        this.books = books
    }
}
