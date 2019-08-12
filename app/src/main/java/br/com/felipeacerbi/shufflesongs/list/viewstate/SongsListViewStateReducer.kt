package br.com.felipeacerbi.shufflesongs.list.viewstate

import br.com.felipeacerbi.shufflesongs.common.viewstate.ViewStateReducer
import br.com.felipeacerbi.shufflesongs.list.repository.model.Song

sealed class SongsListViewStateReducer : ViewStateReducer<SongsListViewState> {
    object ShowLoading : SongsListViewStateReducer() {
        override val updateView: SongsListViewState.() -> Unit = {
            showLoading = true
        }
    }
    data class ShowError(val exception: Exception) : SongsListViewStateReducer() {
        override val updateView: SongsListViewState.() -> Unit = {
            showLoading = false
            showError = true
            errorMessage = exception.message ?: "Unknown error"
        }
    }
    data class ShowData(val list: List<Song>) : SongsListViewStateReducer() {
        override val updateView: SongsListViewState.() -> Unit = {
            showLoading = false
            songsList = list
        }
    }
}