package entity

import case.ListPetSummariesCase.PetSummary

interface Entity

fun Entity.isSame(other: Entity) = isDef() && other.isDef() && id == other.id
fun Entity.isDef() = !isUndef()
fun Entity.isUndef() = id == 0L
fun Iterable<Entity>.nextId() = map { it.id }.maxBy { it } ?: 1

fun <T : Entity> MutableList<T>.replace(entity: T) {
    this[indexOfFirst { it.isSame(entity) }] = entity
}

fun Pet.toPetSummary() = PetSummary(id, name, imageUrl)

val Entity.id: Long
    get() = when (this) {
        is Pet -> id
        is PetSummary -> id
        else -> 0L
    }
