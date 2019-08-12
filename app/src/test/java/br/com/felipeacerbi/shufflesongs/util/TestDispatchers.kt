package br.com.felipeacerbi.shufflesongs.util

import br.com.felipeacerbi.shufflesongs.common.dispatcher.CoroutineDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TestDispatchers : CoroutineDispatchers {
    override fun main(): CoroutineDispatcher = Dispatchers.Unconfined
    override fun io(): CoroutineDispatcher = Dispatchers.Unconfined
    override fun default(): CoroutineDispatcher = Dispatchers.Unconfined
}