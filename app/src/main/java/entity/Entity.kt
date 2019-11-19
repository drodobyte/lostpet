package entity

abstract class Entity(open val id: Long = 0) {
    val undef: Boolean
        get() = id == 0L

    fun isSame(other: Entity): Boolean = id == other.id && javaClass == other.javaClass
}