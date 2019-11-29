package entity

import com.drodobyte.core.kotlin.entity.Entity

data class Pet(
    override val id: Long?,
    val name: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val found: Boolean = false,
    val location: Location = Location()
) : Entity {
    val undefined
        get() = isNew() && name == "" && description == "" && imageUrl == "" && !found && location.undefined
}

