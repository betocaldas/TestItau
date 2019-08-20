package corp.bcapc.remote

import corp.bcapc.usecase.UserInfo
import retrofit2.http.GET

/**
 *   Created by Carlos Alberto(Beto) Pereira Caldas on 11/08/2019 - 03:39
 */

interface Api {

    @GET("/.json")
    suspend fun getUserInfo(): UserInfo

    @GET("")
    suspend fun payTo(contact: String)
}