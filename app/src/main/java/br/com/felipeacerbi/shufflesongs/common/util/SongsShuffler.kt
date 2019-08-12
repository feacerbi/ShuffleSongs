package br.com.felipeacerbi.shufflesongs.common.util

import br.com.felipeacerbi.shufflesongs.list.repository.model.Song

class SongsShuffler {

    fun shuffle(newList: List<Song>): List<Song> {
        var shuffledList = newList.shuffled()

        while(shuffledList.checkConditions().not()) {
            shuffledList = shuffledList.shuffled()
        }

        return shuffledList
    }

    private fun List<Song>.checkConditions(): Boolean = checkListConditions(this)

    fun checkListConditions(list: List<Song>): Boolean {
        var isValid = true

        list.forEachIndexed { index, song ->
            if(index - 1 >= 0) { if(list[index - 1].artist == song.artist) isValid = false }
            if(index + 1 < list.size) { if(list[index + 1].artist == song.artist) isValid = false }
        }

        return isValid
    }

}