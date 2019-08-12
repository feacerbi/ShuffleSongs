package br.com.felipeacerbi.shufflesongs.list.viewstate

import br.com.felipeacerbi.shufflesongs.common.viewstate.ViewState
import br.com.felipeacerbi.shufflesongs.list.repository.model.Song

data class SongsListViewState(
    var showLoading: Boolean = false,
    var showError: Boolean = false,
    var errorMessage: String = "",
    var songsList: List<Song> = listOf()
) : ViewState