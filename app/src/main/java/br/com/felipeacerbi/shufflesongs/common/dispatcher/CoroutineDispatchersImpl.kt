package br.com.felipeacerbi.shufflesongs.common.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CoroutineDispatchersImpl : CoroutineDispatchers {
    override fun main(): CoroutineDispatcher =
        Dispatchers.Main

    override fun io(): CoroutineDispatcher =
        Dispatchers.IO

    override fun default(): CoroutineDispatcher =
        Dispatchers.Default
}