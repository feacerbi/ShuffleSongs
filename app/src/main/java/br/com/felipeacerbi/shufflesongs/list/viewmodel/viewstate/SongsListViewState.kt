package br.com.felipeacerbi.shufflesongs.list.viewmodel.viewstate

import br.com.felipeacerbi.shufflesongs.common.viewstate.ViewState
import br.com.felipeacerbi.shufflesongs.list.model.Song

data class ListViewState(
    var showLoading: Boolean = false,
    var showError: Boolean = false,
    var errorMessage: String = "",
    var songsList: List<Song> = listOf()
) : ViewState