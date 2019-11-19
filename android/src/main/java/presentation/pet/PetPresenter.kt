package presentation.pet

import entity.Pet
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface PetView {
    fun visiblePet(action: (id: Long) -> Unit)
    fun visibleNewPet(action: () -> Unit)
    fun clickedMap(action: () -> Unit)
    fun clickedBack(action: () -> Unit)
    fun showPet(pet: Maybe<Pet>)
    fun showMap()
    fun showErrorSave()
    fun shownPet(): Single<Pet>
    fun goBack()
}

interface PetService {
    fun newPet(): Single<Pet>
    fun pet(id: Long): Maybe<Pet>
    fun save(pet: Pet): Completable
}

class PetPresenter(val view: PetView, val service: PetService) {
    init {
        view.visiblePet {
            view.showPet(service.pet(it))
        }
        view.visibleNewPet {
            view.showPet(service.newPet().toMaybe())
        }
        view.clickedMap {
            view.showMap()
        }
        view.clickedBack {
            view.shownPet()
                .map(service::save)
                .subscribe(
                    { view.goBack() },
                    { view.showErrorSave() }
                )
        }
    }
}