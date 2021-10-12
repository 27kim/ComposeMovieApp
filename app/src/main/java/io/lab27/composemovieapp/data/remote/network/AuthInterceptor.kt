package io.lab27.composemovieapp.data.remote.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request().newBuilder()

//        if (request.header("No-Authentication") == null) {
        //fixme
        //val token = getTokenFromSharedPreference();
        //or use Token Function
//                if (!token.isNullOrEmpty()) {
        val finalToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4ZjM4OTNmYjM2ZGVmNGQ1NzAxYzc3NjRkMmRlZDE5YiIsInN1YiI6IjYwZGJiN2E4ZDk1NDIwMDA1ZDJmMTBhNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.qxRDQrH3CwjO9KmZHWtd-KRovocHYO2sJyo52jy6HYs"
        request
            .addHeader("Authorization", "Bearer $finalToken")
//            .addHeader("Content-Type", "application/json;charset=utf-8")
//                }
//        }
        return chain.proceed(request.build())
    }
}