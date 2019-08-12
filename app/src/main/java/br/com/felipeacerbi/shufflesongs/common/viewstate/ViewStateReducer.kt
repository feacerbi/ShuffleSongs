package br.com.felipeacerbi.shufflesongs.common

interface ViewStateReducer <T : ViewState> {
    val updateView: T.() -> Unit
}