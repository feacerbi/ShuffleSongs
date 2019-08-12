package br.com.felipeacerbi.shufflesongs.list.di

import br.com.felipeacerbi.shufflesongs.list.repository.SongsListRepository
import br.com.felipeacerbi.shufflesongs.list.repository.SongsListRepositoryImpl
import br.com.felipeacerbi.shufflesongs.list.viewmodel.SongsListViewModelImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val songsListModule = module {

    factory<SongsListRepository> { SongsListRepositoryImpl(get()) }

    viewModel { SongsListViewModelImpl(get(), get(), get()) }
}