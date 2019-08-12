package br.com.felipeacerbi.shufflesongs.tests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.felipeacerbi.shufflesongs.common.dispatchers.CoroutineDispatchers
import br.com.felipeacerbi.shufflesongs.common.util.SongsShuffler
import br.com.felipeacerbi.shufflesongs.list.repository.SongsListRepository
import br.com.felipeacerbi.shufflesongs.list.repository.model.Song
import br.com.felipeacerbi.shufflesongs.list.viewmodel.SongsListViewModel
import br.com.felipeacerbi.shufflesongs.list.viewmodel.SongsListViewModel.Action.*
import br.com.felipeacerbi.shufflesongs.list.viewmodel.SongsListViewModelImpl
import br.com.felipeacerbi.shufflesongs.util.FakeListsDataSource
import br.com.felipeacerbi.shufflesongs.util.TestDispatchers
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SongsListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var repository: SongsListRepository

    @MockK
    lateinit var shuffler: SongsShuffler

    private val listsDataSource = FakeListsDataSource

    lateinit var dispatchers: CoroutineDispatchers
    lateinit var viewModel: SongsListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        dispatchers = TestDispatchers()

        viewModel = SongsListViewModelImpl(repository, dispatchers, shuffler)
    }

    @Test
    fun `when action is RequestSongs should request songs for every artist id on Repository`() {
        coEvery { repository.requestSongsList(1) } returns listsDataSource.fakeList1
        coEvery { repository.requestSongsList(2) } returns listsDataSource.fakeList2
        coEvery { repository.requestSongsList(3) } returns listsDataSource.fakeList3
        coEvery { repository.requestSongsList(4) } returns listsDataSource.fakeList4

        viewModel.perform(RequestSongs(intArrayOf(1, 2, 3, 4)))

        coVerify { repository.requestSongsList(1) }
        coVerify { repository.requestSongsList(2) }
        coVerify { repository.requestSongsList(3) }
        coVerify { repository.requestSongsList(4) }
    }

    @Test
    fun `when action is RequestSongs should return all songs for artists ids`() {
        coEvery { repository.requestSongsList(1) } returns listsDataSource.fakeList1
        coEvery { repository.requestSongsList(2) } returns listsDataSource.fakeList2
        coEvery { repository.requestSongsList(3) } returns listsDataSource.fakeList3
        coEvery { repository.requestSongsList(4) } returns listsDataSource.fakeList4

        viewModel.perform(RequestSongs(intArrayOf(1, 2, 3, 4)))

        viewModel.getStateStream().observeForever {
            assertArrayEquals(listsDataSource.fullList.toTypedArray(), it.songsList.toTypedArray())
        }
    }

    @Test
    fun `when action is Shuffle should shuffle current list of songs`() {
        coEvery { repository.requestSongsList(1) } returns listsDataSource.fullList
        every { shuffler.shuffle(listsDataSource.fullList) } returns listsDataSource.fullShuffledList

        viewModel.perform(RequestSongs(intArrayOf(1)))
        viewModel.perform(Shuffle)

        verify(exactly = 1) { shuffler.shuffle(listsDataSource.fullList) }

        viewModel.getStateStream().observeForever {
            assertArrayEquals(listsDataSource.fullShuffledList.toTypedArray(), it.songsList.toTypedArray())
        }
    }
}
