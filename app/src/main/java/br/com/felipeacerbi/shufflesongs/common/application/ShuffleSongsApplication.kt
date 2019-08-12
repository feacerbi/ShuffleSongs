package br.com.felipeacerbi.shufflesongs.common.application

import android.app.Application
import br.com.felipeacerbi.shufflesongs.common.di.appModule
import br.com.felipeacerbi.shufflesongs.common.di.networkModule
import br.com.felipeacerbi.shufflesongs.list.di.songsListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ShuffleSongsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ShuffleSongsApplication)
            modules(listOf(appModule, networkModule, songsListModule))
        }
    }
}