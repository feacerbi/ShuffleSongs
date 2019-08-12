package br.com.felipeacerbi.shufflesongs.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

infix fun ViewGroup.inflate(layoutResId: Int): View =
    LayoutInflater.from(context).inflate(layoutResId, this, false)

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit): LiveData<T> =
    liveData.apply { observe(this@observe, Observer { observable -> observable?.let { action(it) } }) }

fun CoroutineScope.launchSafely(error: (java.lang.Exception) -> Unit = {}, block: suspend CoroutineScope.() -> Unit) {
    launch {
        try {
            block.invoke(this)
        } catch (e: Exception) {
            error.invoke(e)
        }
    }
}

fun <T : ViewState> MutableLiveData<T>.update(reducer: ViewStateReducer<T>) {
    postValue(value?.apply {
        reducer.updateView.invoke(this)
    })
}