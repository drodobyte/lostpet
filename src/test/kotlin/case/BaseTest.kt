package case

import com.drodobyte.core.kotlin.check.Check
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import service.PetService

abstract class BaseTest {

    @Mock
    lateinit var service: PetService

    @BeforeEach
    open fun initMocks() {
        MockitoAnnotations.initMocks(this)
    }

    protected fun <T> Observable<T>.test(observer: (TestObserver<T>) -> Unit) =
        observer(test())

    protected fun <T> Single<T>.test(observer: (TestObserver<T>).() -> Unit) =
        observer(test())

    protected fun Throwable.hasOnly(error: Checker.Error) =
        this is Check.Ex && errors.size == 1 && error in errors
}
