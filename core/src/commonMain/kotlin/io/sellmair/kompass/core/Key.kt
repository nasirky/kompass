package io.sellmair.kompass.core

import kotlin.random.Random

open class Key {

    open val value: String = randomKeyValue()

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Key) return false
        if (value != other.value) return false
        return true
    }

    final override fun hashCode(): Int {
        return value.hashCode()
    }

    companion object Factory

}

fun Key.Factory.randomKeyValue() = Random.nextBytes(16)
    .map { byte -> byte.toInt() and 0xFF }
    .joinToString("") { it.toString(16) }


operator fun Key.Factory.invoke(value: String): Key = DefinedKey(value)

private class DefinedKey(override val value: String) : Key()