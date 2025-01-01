package llp.lifeeasy.cricradio.data.models

sealed class Resource<T>(open val data: T?) {
    class Loading<T> : Resource<T>(null)
    class Success<T>(override val data: T) : Resource<T>(data)
    class Failure<T>(val message: String) : Resource<T>(null)
}