package io.larbin.api.greeting

import javax.persistence.*

@Entity
@Table(name = "greeting")
data class Greeting(val content: String,
                    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long? = null)
