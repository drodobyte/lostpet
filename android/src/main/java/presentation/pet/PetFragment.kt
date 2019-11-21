package presentation.pet

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
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
    private val visiblePet = create<Long>()
    private lateinit var current: Pet

    override fun visiblePet(action: (id: Long) -> Unit) {
        visiblePet.xSubscribe(action)
    }

    override fun showPet(pet: Pet) = with(pet) {
        current = copy()
        pet_name.setText(name)
        if (imageUrl.isNotEmpty())
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
        Toast.makeText(context, "error", Toast.LENGTH_LONG).show()
    }

    override fun filledPet() = Pet(
        current.id,
        pet_name.text.toString(),
        pet_description.text.toString(),
        current.imageUrl,
        current.found,
        current.location
    )

    override fun goBack() {
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
            pet_location_date.xShowDialog(fragmentManager)
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    clickedBack.onNext(Any())
                }
            }
        )
        visiblePet.onNext(go.args.pet().id)
    }
}
