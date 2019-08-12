package br.com.felipeacerbi.shufflesongs.list.viewmodel

import androidx.lifecycle.MutableLiveData
import br.com.felipeacerbi.shufflesongs.list.viewstate.SongsListViewState

interface SongsListViewModel {

    fun getStateStream(): MutableLiveData<SongsListViewState>
    fun perform(action: Action)

    sealed class Action {
        data class RequestSongs(val artistsIds: List<Int>) : Action()
        object Shuffle : Action()
    }
}