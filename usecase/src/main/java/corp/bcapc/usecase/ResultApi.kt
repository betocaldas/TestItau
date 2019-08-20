package corp.bcapc.usecase

/**
 *   Created by Carlos Alberto(Beto) Pereira Caldas on 16/08/2019 - 02:13
 */

sealed class ResultApi<out T>{
    data class Success<out T>(val data: T): ResultApi<T>()
    data class Fail<out T>(val error: String): ResultApi<T>()
    data class Loading<out T>(val isLoading: Boolean): ResultApi<T>()
}