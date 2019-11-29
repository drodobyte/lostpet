package case

import case.Checker.Error
import com.drodobyte.core.kotlin.check.Check.OnError
import entity.Pet
import service.PetService

/**
 * Base Use-Case class
 */
abstract class Case(val service: PetService) {

    fun check(pet: Pet): OnError<Error> =
        Checker.on(pet)
}