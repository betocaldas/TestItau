package corp.bcapc.remote

import corp.bcapc.data.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *   Created by Carlos Alberto(Beto) Pereira Caldas on 11/08/2019 - 04:28
 */

object retrofitController {

    private const val TIMEOUT = Constants.TIMEOUT.toLong()

    fun configService(isDebugging: Boolean, serverUrl: String): Api =
        Retrofit.Builder()
            .baseUrl(serverUrl)
            .client(createHttpClient(createInterceptor(isDebugging)))
            .addConverterFactory(GsonConverterFactory.create())
            .build().run {
                create(Api::class.java)
            }

    private fun createHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(interceptor)
        .callTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
        .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
        .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
        .build()

    private fun createInterceptor(isDebugging: Boolean): HttpLoggingInterceptor =
        if (isDebugging) HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        } else HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.NONE
        }
}