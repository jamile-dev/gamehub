package dev.jamile.data.di.network

import okhttp3.Interceptor
import okhttp3.Response
/**
 * ESRBRatingInterceptor is an OkHttp Interceptor that adds a query parameter to exclude
 * games with mature content (NSFW) from the API requests.
 *
 * *Usage*: Add this interceptor to your OkHttpClient instance to automatically filter out NSFW games
 * from all API requests.
 *
 * Example:
 *  val client = OkHttpClient.Builder()
 *              .addNetworkInterceptor(ESRBRatingInterceptor())
 *              .build()
 */
class ESRBRatingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val newUrlBuilder = originalUrl.newBuilder()
        newUrlBuilder.addQueryParameter("exclude_nsfw", "true")

        val newUrl = newUrlBuilder.build()
        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}