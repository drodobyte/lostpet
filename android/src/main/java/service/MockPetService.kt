package service

import entity.Location
import entity.Pet
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import java.util.*
import kotlin.random.Random.Default.nextBoolean
import kotlin.random.Random.Default.nextDouble
import kotlin.random.Random.Default.nextLong


class MockPetService : PetService {

    override fun pets(): Observable<Pet> = Observable.fromIterable(pets)

    override fun pet(id: Long): Maybe<Pet> = Maybe.just(pets.findLast { it.id == id })

    override fun save(pet: Pet): Completable {
        val i = pets.indexOfFirst { it.isSame(pet) }
        if (i == -1)
            pets.add(pet)
        else
            pets[i] = pet
        return Completable.complete()
    }

    private val pets: MutableList<Pet> by lazy {
        mutableListOf(
            aPet(1003),
            aPet(1007),
            aPet(1023),
            aPet(10263),
            aPet(10715),
            aPet(10822),
            aPet(10982),
            aPet(11172),
            aPet(11182)
        )
    }

    private fun aPet(id: Long): Pet = Pet(
        id, "name_$id", "description_$id",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_$id.jpg",
        nextBoolean(),
        Location(
            Date(System.currentTimeMillis() + nextLong(1000000)),
            nextDouble(10.0),
            nextDouble(10.0),
            nextDouble(10.0)
        )
    )
}