package br.com.felipeacerbi.shufflesongs.util

import br.com.felipeacerbi.shufflesongs.list.repository.model.Song

object FakeListsDataSource {
    val fakeList1: List<Song> by lazy {
        listOf(
            Song("Song1", "Artist1", "Gemnre1", "Picture1"),
            Song("Song2", "Artist1", "Gemnre2", "Picture2"),
            Song("Song3", "Artist1", "Gemnre2", "Picture3"),
            Song("Song4", "Artist1", "Gemnre2", "Picture4")
        )
    }

    val fakeList2: List<Song> by lazy {
        listOf(
            Song("Song21", "Artist2", "Gemnre3", "Picture5"),
            Song("Song22", "Artist2", "Gemnre3", "Picture6"),
            Song("Song23", "Artist2", "Gemnre3", "Picture7"),
            Song("Song24", "Artist2", "Gemnre1", "Picture8")
        )
    }

    val fakeList3: List<Song> by lazy {
        listOf(
            Song("Song31", "Artist3", "Gemnre1", "Picture9"),
            Song("Song32", "Artist3", "Gemnre1", "Picture10"),
            Song("Song33", "Artist3", "Gemnre4", "Picture11"),
            Song("Song34", "Artist3", "Gemnre2", "Picture12")
        )
    }

    val fakeList4: List<Song> by lazy {
        listOf(
            Song("Song41", "Artist4", "Gemnre1", "Picture13"),
            Song("Song42", "Artist4", "Gemnre1", "Picture14")
        )
    }

    val fullList: List<Song> by lazy {
        listOf(
            Song("Song1", "Artist1", "Gemnre1", "Picture1"),
            Song("Song2", "Artist1", "Gemnre2", "Picture2"),
            Song("Song3", "Artist1", "Gemnre2", "Picture3"),
            Song("Song4", "Artist1", "Gemnre2", "Picture4"),
            Song("Song21", "Artist2", "Gemnre3", "Picture5"),
            Song("Song22", "Artist2", "Gemnre3", "Picture6"),
            Song("Song23", "Artist2", "Gemnre3", "Picture7"),
            Song("Song24", "Artist2", "Gemnre1", "Picture8"),
            Song("Song31", "Artist3", "Gemnre1", "Picture9"),
            Song("Song32", "Artist3", "Gemnre1", "Picture10"),
            Song("Song33", "Artist3", "Gemnre4", "Picture11"),
            Song("Song34", "Artist3", "Gemnre2", "Picture12"),
            Song("Song41", "Artist4", "Gemnre1", "Picture13"),
            Song("Song42", "Artist4", "Gemnre1", "Picture14")
        )
    }

    val fullShuffledList: List<Song> by lazy {
        listOf(
            Song("Song2", "Artist1", "Gemnre2", "Picture2"),
            Song("Song23", "Artist2", "Gemnre3", "Picture7"),
            Song("Song1", "Artist1", "Gemnre1", "Picture1"),
            Song("Song21", "Artist2", "Gemnre3", "Picture5"),
            Song("Song3", "Artist1", "Gemnre2", "Picture3"),
            Song("Song22", "Artist2", "Gemnre3", "Picture6"),
            Song("Song4", "Artist1", "Gemnre2", "Picture4"),
            Song("Song31", "Artist3", "Gemnre1", "Picture9"),
            Song("Song41", "Artist4", "Gemnre1", "Picture13"),
            Song("Song32", "Artist3", "Gemnre1", "Picture10"),
            Song("Song24", "Artist2", "Gemnre1", "Picture8"),
            Song("Song33", "Artist3", "Gemnre4", "Picture11"),
            Song("Song42", "Artist4", "Gemnre1", "Picture14"),
            Song("Song34", "Artist3", "Gemnre2", "Picture12")
        )
    }
}