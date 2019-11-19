package app

import android.app.Application
import service.MockPetService
import service.PetService
import util.ViewModelFactory

class App : Application() {

    val service: PetService = MockPetService()
    val viewModelFactory = ViewModelFactory(this)
}