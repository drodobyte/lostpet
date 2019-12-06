package case

import case.Checker.Error.ImageEmpty
import case.Checker.Error.NameEmpty
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.stub
import entity.Pet
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class SavePetCaseTest : BaseTest() {

    lateinit var case: SavePetCase

    @BeforeEach
    fun before() {
        case = SavePetCase(service)
    }

    @Test
    fun `Saving pet with blank name is error`() {
        val pet = Pet(null, "  ", "desc", "https://test")

        val save = case.save(pet)

        save.test {
            assertError { it.hasOnly(NameEmpty) }
            assertNoValues()
        }
    }

    @Test
    fun `Saving pet with blank imageUrl is error`() {
        val pet = Pet(null, "rufus", "desc", "  ")

        val save = case.save(pet)

        save.test {
            assertError { it.hasOnly(ImageEmpty) }
            assertNoValues()
        }
    }

    @Test
    fun `Saving pet with name and imageUrl is ok`() {
        val validPet = Pet(null, "rufus", "desc", "http://test")
        service.stub {
            on {
                save(any())
            }.thenReturn(
                Single.just(validPet)
            )
        }

        val save = case.save(validPet)

        save.test {
            assertNoErrors()
            assertComplete()
            assertValues(validPet.copy())
        }
    }
}