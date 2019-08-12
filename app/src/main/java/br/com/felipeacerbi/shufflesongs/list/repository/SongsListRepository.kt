package br.com.felipeacerbi.shufflesongs.list.repository

import br.com.felipeacerbi.shufflesongs.list.model.Song

interface ListRepository  {
    suspend fun requestSongsList(artistId: Int): List<Song>
}