package service

import entity.Pet
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable

interface PetService {

    fun pets(): Observable<Pet>

    fun pet(id: Long): Maybe<Pet>

    fun save(pet: Pet): Completable
}