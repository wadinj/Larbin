package io.larbin.api.greeting

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "greeting")
data class Greeting(
        @Id val id: Long,
        val content: String)
