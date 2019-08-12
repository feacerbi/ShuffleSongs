package br.com.felipeacerbi.shufflesongs.common.di

import br.com.felipeacerbi.shufflesongs.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single<HttpLoggingInterceptor> { provideHttpLoggingInterceptor() }
    single<OkHttpClient> { provideDefaultOkhttpClient(get()) }
    single<Retrofit> { provideRetrofit(get()) }

}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.NONE
    }

    return httpLoggingInterceptor
}

fun provideDefaultOkhttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://us-central1-tw-exercicio-mobile.cloudfunctions.net/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}