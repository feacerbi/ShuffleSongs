package br.com.felipeacerbi.shufflesongs.list.repository.mapper

import br.com.felipeacerbi.shufflesongs.list.repository.model.Song
import br.com.felipeacerbi.shufflesongs.list.service.model.SongsResponse

fun SongsResponse.toSongsList() =
    results.map {
        Song(
            it?.trackName ?: "",
            it?.artistName ?: "",
            it?.primaryGenreName ?: "",
            it?.artworkUrl ?: "")
    }.filter { it.name.isNotEmpty() }