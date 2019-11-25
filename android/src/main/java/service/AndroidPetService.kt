package service

import android.content.Context
import entity.Pet
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers.io

class AndroidPetService(context: Context) : PetService {

    private val rest: RestApi by lazy {
        RestApi
    }
    private val db: DbApi by lazy {
        DbApi.context = context
        DbApi
    }

    override fun pets(): Observable<Pet> {
        return rest.pets().setup()
    }

    override fun pet(id: Long): Maybe<Pet> {
        return rest.pet(id).setup()
    }

    override fun save(pet: Pet): Single<Pet> {
        return rest.save(pet).setup()
    }

    private fun <T> Observable<T>.setup() = subscribeOn(io()).observeOn(mainThread())
    private fun <T> Maybe<T>.setup() = subscribeOn(io()).observeOn(mainThread())
    private fun <T> Single<T>.setup() = subscribeOn(io()).observeOn(mainThread())
}