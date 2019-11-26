package entity

data class Pet(
    val id: Long = NEW,
    val name: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val found: Boolean = false,
    val location: Location = Location()
) : Entity {
    val undefined
        get() = id == NEW && name == "" && description == "" && imageUrl == "" && !found && location.undefined
}

