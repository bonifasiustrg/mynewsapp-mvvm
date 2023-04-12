package com.bonifasiustrg.newsappmvvm.utils

//Class used to wrap around the network response, like error or succesfulll response, and also handling the loading
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(messege: String, data: T? = null): Resource<T>(data, messege)
    class Loading<T>: Resource<T>()
}