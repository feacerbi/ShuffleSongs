package br.com.felipeacerbi.shufflesongs.common.di

import br.com.felipeacerbi.shufflesongs.common.dispatchers.CoroutineDispatchers
import br.com.felipeacerbi.shufflesongs.common.dispatchers.CoroutineDispatchersImpl
import org.koin.dsl.module

val appModule = module {
    single<CoroutineDispatchers> { CoroutineDispatchersImpl() }
}