package br.com.felipeacerbi.shufflesongs.list.repository

import br.com.felipeacerbi.shufflesongs.list.repository.mapper.toSongsList
import br.com.felipeacerbi.shufflesongs.list.repository.model.Song
import br.com.felipeacerbi.shufflesongs.list.service.SongsService

class SongsListRepositoryImpl(
    private val songsService: SongsService
) : SongsListRepository {

    override suspend fun requestSongsList(artistId: Int): List<Song> {
        return songsService.getSongsByArtist(artistId).toSongsList()
    }
}