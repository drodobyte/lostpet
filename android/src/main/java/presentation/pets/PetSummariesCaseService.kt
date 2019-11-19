package presentation.pets

import case.ListPetSummariesCase
import case.ListPetSummariesCase.PetSummary
import io.reactivex.Observable

class PetSummariesCaseService(val case: ListPetSummariesCase) : PetSummariesService {

    override fun allPets(): Observable<PetSummary> = case.listAll()

    override fun foundPets(): Observable<PetSummary> = case.listFound()

    override fun lostPets(): Observable<PetSummary> = case.listLost()
}
