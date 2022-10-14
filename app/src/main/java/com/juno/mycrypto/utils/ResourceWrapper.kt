package com.juno.mycrypto.utils


data class ResourceWrapper<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): ResourceWrapper<T> =
            ResourceWrapper(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): ResourceWrapper<T> =
            ResourceWrapper(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): ResourceWrapper<T> =
            ResourceWrapper(status = Status.LOADING, data = data, message = null)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}