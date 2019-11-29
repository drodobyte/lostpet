package case

import entity.Pet
import io.reactivex.Single
import service.PetService

class SavePetCase(service: PetService) : Case(service) {

    fun save(pet: Pet): Single<Pet> =
        check(pet).onError.isEmpty.flatMap {
            service.save(pet)
        }
}