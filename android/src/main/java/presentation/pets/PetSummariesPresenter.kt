package presentation.pets

import case.ListPetSummariesCase.PetSummary
import io.reactivex.Observable
import presentation.pets.Filter.*

interface PetSummariesView {
    fun visible(action: () -> Unit)
    fun clickedFilter(action: (Filter) -> Unit)
    fun clickedPetSummary(action: (id: Long) -> Unit)
    fun clickedNewPet(action: () -> Unit)
    fun showSummaries(summaries: Observable<PetSummary>)
    fun showNewPet()
    fun showPet(id: Long)
}

interface PetSummariesService {
    fun allPets(): Observable<PetSummary>
    fun foundPets(): Observable<PetSummary>
    fun lostPets(): Observable<PetSummary>
}

class PetSummariesPresenter(view: PetSummariesView, service: PetSummariesService) {
    init {
        view.visible {
            view.showSummaries(service.allPets())
        }
        view.clickedFilter {
            view.showSummaries(
                when (it) {
                    All -> service.allPets()
                    Found -> service.foundPets()
                    Lost -> service.lostPets()
                }
            )
        }
        view.clickedNewPet {
            view.showNewPet()
        }
        view.clickedPetSummary {
            view.showPet(it)
        }
    }
}

enum class Filter {
    All, Found, Lost
}