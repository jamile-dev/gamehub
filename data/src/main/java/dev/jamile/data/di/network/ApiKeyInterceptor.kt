package dev.jamile.data.di.network

import dev.jamile.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val urlWithApiKey =
            originalUrl
                .newBuilder()
                .addQueryParameter("key", BuildConfig.API_KEY)
                .build()

        val requestWithApiKey =
            originalRequest
                .newBuilder()
                .url(urlWithApiKey)
                .build()

        return chain.proceed(requestWithApiKey)
    }
}
