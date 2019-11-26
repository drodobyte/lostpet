package presentation.pet

import android.os.Bundle
import android.view.View
import app.Container
import case.SavePetCase
import case.ShowPetCase
import drodobytecom.lostpet.R
import entity.Pet
import io.reactivex.subjects.PublishSubject.create
import kotlinx.android.synthetic.main.pet_fragment.*
import util.*

class PetFragment : AppFragment(), PetView {

    override fun layout(): Int = R.layout.pet_fragment

    private val clickedMap = create<Any>()
    private val clickedBack = create<Any>()
    private val visiblePet = create<Pair<Long, Boolean>>()

    override fun visiblePet(action: (Long, Boolean) -> Unit) {
        visiblePet.xSubscribe { (id, editing) -> action(id, editing) }
    }

    override fun showPet(pet: Pet) = with(pet) {
        petViewModel.pet = copy()
        pet_name.setText(name)
        pet_image.xLoadPet(imageUrl)
        pet_found.xShow(found)
        pet_description.setText(description)
        pet_location_date.xDate(location.date)
    }

    override fun showMap() {
        go.petLocation()
    }

    override fun clickedMap(action: () -> Unit) {
        clickedMap.xSubscribe(action)
    }

    override fun clickedBack(action: () -> Unit) {
        clickedBack.xSubscribe(action)
    }

    override fun showErrorSave() {
        showError("Error saving pet!")
    }

    override fun filledPet() = Pet(
        petViewModel.pet.id,
        pet_name.text.toString(),
        pet_description.text.toString(),
        petViewModel.pet.imageUrl,
        petViewModel.pet.found,
        petViewModel.pet.location
    )

    override fun goBack() {
        petViewModel.clear()
        go.back()
    }

    override fun onViewCreated(view: View, saved: Bundle?) {
        PetPresenter(
            this,
            PetCaseService(
                ShowPetCase(Container.petService),
                SavePetCase(Container.petService)
            )
        )
        pet_location_pin.setOnClickListener {
            clickedMap.onNext(Any())
        }
        pet_location_date.setOnClickListener {
            petViewModel.pet.location.date.xShowDialog(fragmentManager) {
                pet_location_date.text = it.xFormatted()
            }
        }
        requireActivity().onBackPressed {
            clickedBack.onNext(Any())
        }
        visiblePet.onNext(Pair(go.args.pet().id, !petViewModel.pet.undefined))
    }
}
