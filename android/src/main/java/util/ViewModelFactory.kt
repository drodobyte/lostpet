package util

import app.App

class ViewModelFactory(val app: App)
//    : ViewModelProvider.Factory
{

//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
//        when (modelClass) {
//            PetsPresenter::class.java -> PetsPresenter(ListPetSummariesCase(app.service))
//            PetPresenter::class.java -> PetPresenter(
//                ShowPetCase(app.service), SavePetCase(app.service)
//            )
//            else -> PetsPresenter(ListPetSummariesCase(app.service))
//        } as T
}