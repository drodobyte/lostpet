package service

import entity.Pet
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers.io

class AndroidPetService : PetService {

    private val api: RestApi by lazy {
        RestApi
    }

    override fun pets(): Observable<Pet> {
        return api.pets().setup()
    }

    override fun pet(id: Long): Maybe<Pet> {
        return api.pet(id).setup()
    }

    override fun save(pet: Pet): Single<Pet> {
        return api.save(pet).setup()
    }

    private fun <T> Observable<T>.setup() = subscribeOn(io()).observeOn(mainThread())
    private fun <T> Maybe<T>.setup() = subscribeOn(io()).observeOn(mainThread())
    private fun <T> Single<T>.setup() = subscribeOn(io()).observeOn(mainThread())
}