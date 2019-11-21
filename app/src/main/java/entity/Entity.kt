package entity

import case.ListPetSummariesCase.PetSummary

interface Entity

const val NEW = 0L
fun Entity.isSame(other: Entity) = !isNew() && !other.isNew() && id == other.id
fun Entity.isNew(): Boolean = id == NEW

fun Iterable<Entity>.nextId() = map { it.id }.maxBy { it } ?: NEW + 1

fun <T : Entity> MutableList<T>.replace(entity: T) {
    this[indexOfFirst { it.isSame(entity) }] = entity
}

fun Pet.toPetSummary() = PetSummary(id, name, imageUrl)

val Entity.id: Long
    get() = when (this) {
        is Pet -> id
        is PetSummary -> id
        else -> NEW
    }
