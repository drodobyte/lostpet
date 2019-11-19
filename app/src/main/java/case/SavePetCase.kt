package case

import entity.Pet
import io.reactivex.Completable
import service.PetService

class SavePetCase(service: PetService) : Case(service) {

    fun save(pet: Pet): Completable = service.save(pet)
}