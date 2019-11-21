package app

import drodobytecom.lostpet.BuildConfig
import service.AndroidPetService
import service.MockPetService
import service.PetService

object Container {
    const val usingMock = BuildConfig.BUILD_TYPE == "mock"
    val petService: PetService = if (usingMock) MockPetService() else AndroidPetService()
}