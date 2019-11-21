package service

import entity.Pet
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

internal object RestApi : PetService {

    override fun pets(): Observable<Pet> = api.pets().flatMapIterable { it.data }

    override fun pet(id: Long): Maybe<Pet> = api.pet(id)

    override fun save(pet: Pet): Single<Pet> = api.save(pet)


    private val api: Api by lazy {
        RetrofitRemRestApiBuilder.create(Api::class.java)
    }

    private interface Api {

        @GET("pets")
        fun pets(): Observable<PetsData>

        @GET("pets/{id}")
        fun pet(@Path("id") id: Long): Maybe<Pet>

        @POST("pets")
        fun save(@Body pet: Pet): Single<Pet>
    }

    private data class PetsData(val data: List<Pet>)
}

