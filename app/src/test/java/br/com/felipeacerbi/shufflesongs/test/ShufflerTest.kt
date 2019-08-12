package br.com.felipeacerbi.shufflesongs.tests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.felipeacerbi.shufflesongs.common.util.SongsShuffler
import br.com.felipeacerbi.shufflesongs.util.FakeListsDataSource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ShufflerTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val listsDataSource = FakeListsDataSource

    lateinit var shuffler: SongsShuffler

    @Before
    fun setUp() {
        shuffler = SongsShuffler()
    }

    @Test
    fun `when a list of songs is shuffled should meet the conditions`() {
        val shuffledList = shuffler.shuffle(listsDataSource.fullList)

        assertTrue(shuffler.checkListConditions(shuffledList))
    }

}
