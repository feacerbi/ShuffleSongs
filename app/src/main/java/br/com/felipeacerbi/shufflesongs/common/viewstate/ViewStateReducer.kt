package br.com.felipeacerbi.shufflesongs.common.viewstate

interface ViewStateReducer <T : ViewState> {
    val updateView: T.() -> Unit
}