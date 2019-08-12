package br.com.felipeacerbi.shufflesongs.list.service

import br.com.felipeacerbi.shufflesongs.list.service.model.SongsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SongsService {
    @GET("lookup")
    suspend fun getSongsByArtist(@Query("id") id: Int): SongsResponse
}