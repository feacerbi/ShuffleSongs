package br.com.felipeacerbi.shufflesongs.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.felipeacerbi.shufflesongs.R
import br.com.felipeacerbi.shufflesongs.common.extension.observe
import br.com.felipeacerbi.shufflesongs.list.view.adapter.SongsAdapter
import br.com.felipeacerbi.shufflesongs.list.viewmodel.SongsListViewModel
import br.com.felipeacerbi.shufflesongs.list.viewmodel.SongsListViewModel.Action.RequestSongs
import br.com.felipeacerbi.shufflesongs.list.viewmodel.SongsListViewModel.Action.Shuffle
import br.com.felipeacerbi.shufflesongs.list.viewmodel.SongsListViewModelImpl
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class SongsListFragment : Fragment() {

    private val songsListViewModel: SongsListViewModelImpl by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        bindViews()
        send(RequestSongs(resources.getIntArray(R.array.artists_ids).toList()))
    }

    private fun bindViews() {
        songs_list.layoutManager = LinearLayoutManager(requireContext())

        observe(songsListViewModel.getStateStream()) {
            progress.isVisible = it.showLoading
            songs_list.isVisible = it.showLoading.not() && it.showError.not()
            songs_list.adapter = SongsAdapter(it.songsList.toMutableList())
            error.isVisible = it.showError
            error_message.text = it.errorMessage
        }
    }

    private fun setupToolbar() {
        toolbar.title = getString(R.string.app_name)
        toolbar.inflateMenu(R.menu.list_menu)
        toolbar.setOnMenuItemClickListener {
            if(it.itemId == R.id.action_shuffle) send(Shuffle)
            true
        }
    }

    private fun send(action: SongsListViewModel.Action) {
        songsListViewModel.perform(action)
    }

    @VisibleForTesting
    fun getIdlingResource() = songsListViewModel.getIdlingResource()
}
