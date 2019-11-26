package presentation.pet

import entity.NEW
import entity.Pet
import io.reactivex.Maybe
import io.reactivex.Single

class PetPresenter(view: PetView, service: PetService) {
    init {
        view.visiblePet { id, editing ->
            if (editing)
                view.showPet(view.filledPet())
            else
                when (id) {
                    NEW -> service.newPet().toMaybe()
                    else -> service.pet(id)
                }.subscribe(view::showPet)
        }
        view.clickedMap {
            view.showMap()
        }
        view.clickedBack {
            service.save(view.filledPet())
                .subscribe(
                    { view.goBack() },
                    { view.showErrorSave() }
                )
        }
    }
}

interface PetView {
    fun visiblePet(action: (id: Long, editing: Boolean) -> Unit)
    fun clickedMap(action: () -> Unit)
    fun clickedBack(action: () -> Unit)
    fun showPet(pet: Pet)
    fun showMap()
    fun showErrorSave()
    fun filledPet(): Pet
    fun goBack()
}

interface PetService {
    fun newPet(): Single<Pet>
    fun pet(id: Long): Maybe<Pet>
    fun save(pet: Pet): Single<Pet>
}
