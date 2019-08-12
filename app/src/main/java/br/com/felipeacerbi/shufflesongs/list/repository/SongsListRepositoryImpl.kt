package br.com.felipeacerbi.shufflesongs.list.repository

import br.com.felipeacerbi.shufflesongs.list.model.Song
import br.com.felipeacerbi.shufflesongs.list.repository.mapper.toSongsList
import br.com.felipeacerbi.shufflesongs.list.service.SongsService

class ListRepositoryImpl(
    private val songsService: SongsService
) : ListRepository {

    override suspend fun requestSongsList(artistId: Int): List<Song> {
        return songsService.getSongsByArtist(artistId).toSongsList()
    }
}