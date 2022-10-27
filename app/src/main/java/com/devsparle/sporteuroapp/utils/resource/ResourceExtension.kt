package com.devsparle.sporteuroapp.utils.resource

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LiveData<Resource<T>>.observeResource(
    owner: LifecycleOwner,
    loading: (() -> Unit),
    error: ((Exception) -> Unit),
    success: ((T?) -> Unit),
    successWithoutContent: (() -> Unit)? = null
) {
    val observer = ResourceObserver<T>(
        loading = loading,
        success = success,
        successWithoutContent = successWithoutContent,
        error = error
    )
    observe(owner, observer)
}
