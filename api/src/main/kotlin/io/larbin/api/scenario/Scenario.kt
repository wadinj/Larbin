package io.larbin.api.scenario

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "scenarios")
data class Scenario(@Id val id: Long, val target: String?, val patterns: List<String>?, val useJavascript: Boolean?) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Scenario

        if (id != other.id) return false
        if (target != other.target) return false
        if (useJavascript != other.useJavascript) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (target?.hashCode() ?: 0)
        if (useJavascript != null) {
            result = 31 * result + useJavascript.hashCode()
        }
        return result
    }
}
