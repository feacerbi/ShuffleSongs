package br.com.felipeacerbi.shufflesongs.list.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.test.espresso.idling.CountingIdlingResource
import br.com.felipeacerbi.shufflesongs.common.dispatcher.CoroutineDispatchers
import br.com.felipeacerbi.shufflesongs.common.extension.launchSafely
import br.com.felipeacerbi.shufflesongs.common.extension.update
import br.com.felipeacerbi.shufflesongs.common.util.SongsShuffler
import br.com.felipeacerbi.shufflesongs.list.repository.SongsListRepository
import br.com.felipeacerbi.shufflesongs.list.repository.model.Song
import br.com.felipeacerbi.shufflesongs.list.viewmodel.SongsListViewModel.Action
import br.com.felipeacerbi.shufflesongs.list.viewmodel.SongsListViewModel.Action.RequestSongs
import br.com.felipeacerbi.shufflesongs.list.viewmodel.SongsListViewModel.Action.Shuffle
import br.com.felipeacerbi.shufflesongs.list.viewstate.SongsListViewState
import br.com.felipeacerbi.shufflesongs.list.viewstate.SongsListViewStateReducer.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class SongsListViewModelImpl(
    private val repository: SongsListRepository,
    private val dispatchers: CoroutineDispatchers,
    private val shuffler: SongsShuffler
) : ViewModel(), SongsListViewModel, CoroutineScope {

    private val state: MutableLiveData<SongsListViewState> = MutableLiveData(SongsListViewState())
    private val currentList: MutableList<Song> = mutableListOf()
    private val idlingResource = CountingIdlingResource(REQUEST_LIST_IDLING_RESOURCE)

    override fun getStateStream(): MutableLiveData<SongsListViewState> = state

    override fun perform(action: Action) {
        when(action) {
            is RequestSongs -> { requestSongs(action.artistsIds) }
            is Shuffle -> { shuffleSongs() }
        }
    }

    private fun requestSongs(ids: List<Int>) {
        state.update(ShowLoading)

        launchSafely(idlingResource, ::showError) {
            currentList.clear()

            for(id in ids) {
                currentList += withContext(dispatchers.io()) { repository.requestSongsList(id) }
            }

            state.update(ShowData(currentList))
        }
    }

    private fun shuffleSongs() {
        state.update(ShowLoading)

        launchSafely(idlingResource, ::showError) {
            val shuffledList = withContext(dispatchers.default()) { shuffler.shuffle(currentList) }

            state.update(ShowData(shuffledList))
        }
    }

    private fun showError(exception: Exception) {
        state.update(ShowError(exception))
    }

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext + dispatchers.main()

    @VisibleForTesting
    fun getIdlingResource() = idlingResource

    companion object {
        const val REQUEST_LIST_IDLING_RESOURCE = "REQUEST_LIST_IDLING_RESOURCE"
    }
}