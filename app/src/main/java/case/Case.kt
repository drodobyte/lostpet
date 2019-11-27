package case

import case.Checker.Error
import entity.Pet
import service.PetService
import util.Check.OnError

/**
 * Base Use-Case class
 */
abstract class Case(val service: PetService) {

    fun check(pet: Pet): OnError<Error> =
        Checker.on(pet)
}