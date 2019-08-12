package br.com.felipeacerbi.shufflesongs.common.di

import br.com.felipeacerbi.shufflesongs.common.dispatcher.CoroutineDispatchers
import br.com.felipeacerbi.shufflesongs.common.dispatcher.CoroutineDispatchersImpl
import br.com.felipeacerbi.shufflesongs.common.util.SongsShuffler
import org.koin.dsl.module

val appModule = module {
    single<CoroutineDispatchers> { CoroutineDispatchersImpl() }
    factory { SongsShuffler() }
}