package entity

data class Pet(
    override val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val found: Boolean = false,
    val location: Location = Location()
) : Entity(id)

