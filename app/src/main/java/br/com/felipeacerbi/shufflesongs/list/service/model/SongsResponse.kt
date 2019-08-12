package br.com.felipeacerbi.shufflesongs.list.service.model

data class SongsResponse(
    val resultCount: Int,
    val results: List<SongResponseItem?>
)