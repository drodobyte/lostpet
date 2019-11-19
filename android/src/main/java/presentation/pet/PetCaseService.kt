package presentation.pet

import case.SavePetCase
import case.ShowPetCase
import entity.Pet
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class PetCaseService(val showCase: ShowPetCase, val saveCase: SavePetCase) :
    PetService {

    override fun newPet(): Single<Pet> = Single.just(Pet())

    override fun pet(id: Long): Maybe<Pet> = showCase.show(id)

    override fun save(pet: Pet): Completable = saveCase.save(pet)
}
