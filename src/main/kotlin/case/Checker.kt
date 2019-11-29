package case

import case.Checker.Error.ImageEmpty
import case.Checker.Error.NameEmpty
import com.drodobyte.core.kotlin.check.Check
import entity.Pet

object Checker {

    fun on(pet: Pet) = Check().on<Error> {
        isNotBlank(pet.name, NameEmpty)
        isNotBlank(pet.imageUrl, ImageEmpty)
    }

    enum class Error {
        NameEmpty, ImageEmpty
    }
}