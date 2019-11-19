package case

import entity.Entity
import entity.Pet
import io.reactivex.Observable
import service.PetService

class ListPetSummariesCase(service: PetService) : Case(service) {

    fun listAll(): Observable<PetSummary> =
        service.pets()
            .map(this::asPetSummary)

    fun listFound(): Observable<PetSummary> =
        service.pets()
            .filter { it.found }
            .map(this::asPetSummary)

    fun listLost(): Observable<PetSummary> =
        service.pets()
            .filter { !it.found }
            .map(this::asPetSummary)

    data class PetSummary(
        override val id: Long = 0,
        val name: String = "",
        val imageUrl: String = ""
    ) :
        Entity(id)

    private fun asPetSummary(pet: Pet): PetSummary = PetSummary(pet.id, pet.name, pet.imageUrl)
}