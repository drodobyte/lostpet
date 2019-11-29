package case

import entity.Pet
import io.reactivex.Maybe
import service.PetService

class ShowPetCase(service: PetService) : Case(service) {

    fun show(id: Long): Maybe<Pet> = service.pet(id)
}