package br.com.felipeacerbi.shufflesongs.common.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchers {
    fun main(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
    fun default(): CoroutineDispatcher
}