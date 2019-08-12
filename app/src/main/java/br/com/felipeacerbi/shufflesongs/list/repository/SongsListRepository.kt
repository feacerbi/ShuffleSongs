package br.com.felipeacerbi.shufflesongs.list.repository

import br.com.felipeacerbi.shufflesongs.list.repository.model.Song

interface SongsListRepository  {
    suspend fun requestSongsList(artistId: Int): List<Song>
}