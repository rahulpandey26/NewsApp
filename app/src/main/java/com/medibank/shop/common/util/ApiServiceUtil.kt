package com.medibank.shop.common.util

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class ApiServiceUtil(private val context: Context) {

    private var mRetrofit: Retrofit? = null

    init {
        mRetrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClientBuilder().build())
            .build()
    }

   companion object {
       private var sInstance: ApiServiceUtil? = null
       fun getInstance(context: Context): ApiServiceUtil {
           if (sInstance == null) {
               sInstance = ApiServiceUtil(context)
           }
           return sInstance as ApiServiceUtil
       }
   }


    private fun getOkHttpClientBuilder(): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
              //  .addInterceptor(headerInterceptor)

        return httpClient
    }

    private var headerInterceptor: Interceptor = object : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "ecf41ddc61484f218c33c11bff044387")
                .build()
            return chain.proceed(request)
        }
    }

    fun getApiInterface(): ApiInterface {
        return mRetrofit!!.create(ApiInterface::class.java)
    }
}