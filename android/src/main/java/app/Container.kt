package app

import service.MockPetService
import service.PetService

object Container {
    val petService: PetService = MockPetService()
}