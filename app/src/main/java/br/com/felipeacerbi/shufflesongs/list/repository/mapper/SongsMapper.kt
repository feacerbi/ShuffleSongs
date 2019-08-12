package br.com.felipeacerbi.shufflesongs.list.repository.mapper

import br.com.felipeacerbi.shufflesongs.list.model.Song
import br.com.felipeacerbi.shufflesongs.list.service.model.SongsResponse

fun SongsResponse.toSongsList() = songResponseItems.map {
    Song(
        it.trackName,
        it.artistName,
        it.primaryGenreName,
        it.artworkUrl)
}