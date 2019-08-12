package br.com.felipeacerbi.shufflesongs.list.view

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.felipeacerbi.shufflesongs.R
import br.com.felipeacerbi.shufflesongs.list.view.adapter.SongsAdapter
import br.com.felipeacerbi.shufflesongs.list.viewmodel.ListViewModel
import br.com.felipeacerbi.shufflesongs.list.viewmodel.ListViewModel.Action.RequestSongs
import br.com.felipeacerbi.shufflesongs.list.viewmodel.ListViewModel.Action.Shuffle
import br.com.felipeacerbi.shufflesongs.list.viewmodel.ListViewModelImpl
import br.com.felipeacerbi.shufflesongs.common.extension.observe
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels<ListViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showSplashScreen()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupToolbar()
        bindViews()
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        send(RequestSongs(resources.getIntArray(R.array.artists_ids)))
    }

    private fun bindViews() {
        songs_list.layoutManager = LinearLayoutManager(requireContext())

        observe(viewModel.getStateStream()) {
            progress.isVisible = it.showLoading
            songs_list.adapter = SongsAdapter(it.songsList.toMutableList())
        }
    }

    private fun send(action: ListViewModel.Action) {
        viewModel.perform(action)
    }

    private fun setupToolbar() {
        setHasOptionsMenu(true)
        requireActivity().toolbar.visibility = View.VISIBLE
    }

    private fun showSplashScreen() {
        findNavController().navigate(R.id.action_listFragment_to_splashFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_shuffle -> {
                send(Shuffle)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
