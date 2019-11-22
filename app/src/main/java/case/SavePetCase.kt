package case

import entity.Pet
import service.PetService

class SavePetCase(service: PetService) : Case(service) {

    fun save(pet: Pet) = service.save(pet)
}