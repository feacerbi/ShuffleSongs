package br.com.felipeacerbi.shufflesongs.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.felipeacerbi.shufflesongs.common.util.SongsShuffler
import br.com.felipeacerbi.shufflesongs.common.extension.launchSafely
import br.com.felipeacerbi.shufflesongs.list.model.Song
import br.com.felipeacerbi.shufflesongs.list.repository.ListRepository
import br.com.felipeacerbi.shufflesongs.list.viewmodel.ListViewModel.Action
import br.com.felipeacerbi.shufflesongs.list.viewmodel.ListViewModel.Action.RequestSongs
import br.com.felipeacerbi.shufflesongs.list.viewmodel.ListViewModel.Action.Shuffle
import br.com.felipeacerbi.shufflesongs.list.viewmodel.ListViewModel.State
import br.com.felipeacerbi.shufflesongs.list.viewmodel.ListViewModel.State.*
import br.com.felipeacerbi.shufflesongs.list.viewmodel.viewstate.ListViewState
import br.com.felipeacerbi.shufflesongs.common.extension.update
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class ListViewModelImpl(
    private val repository: ListRepository,
    private val uiDispatcher: CoroutineDispatcher,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel(), ListViewModel, CoroutineScope {

    private val state: MutableLiveData<ListViewState> = MutableLiveData(ListViewState())
    private val currentList: MutableList<Song> = mutableListOf()
    private val shuffler: SongsShuffler = SongsShuffler(currentList)

    override fun getStateStream(): MutableLiveData<ListViewState> = state

    override fun perform(action: Action) {
        when(action) {
            is RequestSongs -> { requestSongs(action.artistsIds.toList()) }
            is Shuffle -> { shuffleSongs() }
        }
    }

    private fun requestSongs(ids: List<Int>) {
        state.update(ShowLoading)

        launchSafely(::showError) {
            currentList.clear()

            for(id in ids) {
                currentList += withContext(ioDispatcher) { repository.requestSongsList(id) }
            }

            state.update(ShowData(currentList))
        }
    }

    private fun shuffleSongs() {
        state.update(ShowData(shuffler.shuffle()))
    }

    private fun showError(exception: Exception) {
        state.update(ShowError(exception))
    }

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext + uiDispatcher
}