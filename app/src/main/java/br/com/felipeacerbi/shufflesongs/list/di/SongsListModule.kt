package br.com.felipeacerbi.shufflesongs.list.di

import br.com.felipeacerbi.shufflesongs.list.repository.SongsListRepository
import br.com.felipeacerbi.shufflesongs.list.repository.SongsListRepositoryImpl
import br.com.felipeacerbi.shufflesongs.list.service.SongsService
import br.com.felipeacerbi.shufflesongs.list.viewmodel.SongsListViewModelImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val songsListModule = module {
    single { provideSongsService(get()) }

    factory<SongsListRepository> { SongsListRepositoryImpl(get()) }

    viewModel { SongsListViewModelImpl(get(), get(), get()) }
}

fun provideSongsService(retrofit: Retrofit): SongsService = retrofit.create(SongsService::class.java)