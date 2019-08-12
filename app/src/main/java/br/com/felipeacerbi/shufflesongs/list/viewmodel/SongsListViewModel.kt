package br.com.felipeacerbi.shufflesongs.list.viewmodel

import androidx.lifecycle.MutableLiveData
import br.com.felipeacerbi.shufflesongs.common.viewstate.ViewStateReducer
import br.com.felipeacerbi.shufflesongs.list.model.Song
import br.com.felipeacerbi.shufflesongs.list.viewmodel.viewstate.ListViewState
import java.lang.Exception

interface ListViewModel {

    fun getStateStream(): MutableLiveData<ListViewState>
    fun perform(action: Action)

    sealed class Action {
        data class RequestSongs(val artistsIds: IntArray) : Action()
        object Shuffle : Action()
    }

    sealed class State : ViewStateReducer<ListViewState> {
        object ShowLoading : State() {
            override val updateView: ListViewState.() -> Unit = {
                showLoading = true
            }
        }
        data class ShowError(val exception: Exception) : State() {
            override val updateView: ListViewState.() -> Unit = {
                showLoading = false
                errorMessage = exception.message ?: "Unknown error"
                showError = true
            }
        }
        data class ShowData(val list: List<Song>) : State() {
            override val updateView: ListViewState.() -> Unit = {
                songsList = list
                showLoading = false
            }
        }
    }
}