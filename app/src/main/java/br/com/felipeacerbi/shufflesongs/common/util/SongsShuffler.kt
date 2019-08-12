package br.com.felipeacerbi.shufflesongs.common

import br.com.felipeacerbi.shufflesongs.list.model.Song

class SongsShuffler(
    private val list: List<Song>
) {

    fun shuffle(newList: List<Song> = list): List<Song> {
        val shuffledList = newList.shuffled()

        return if(shuffledList.checkConditions()) shuffledList
        else shuffle(shuffledList)
    }

    private fun List<Song>.checkConditions(): Boolean {
        var isValid = true

        forEachIndexed { index, song ->
            if(index - 1 >= 0) { if(this[index - 1].artist == song.artist) isValid = false }
            if(index + 1 < size) { if(this[index + 1].artist == song.artist) isValid = false }
        }

        return isValid
    }

}