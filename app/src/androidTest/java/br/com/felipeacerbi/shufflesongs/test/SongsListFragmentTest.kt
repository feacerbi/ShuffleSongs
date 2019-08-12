package br.com.felipeacerbi.shufflesongs.test

import androidx.annotation.IdRes
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import br.com.felipeacerbi.shufflesongs.R
import br.com.felipeacerbi.shufflesongs.list.view.SongsListFragment
import br.com.felipeacerbi.shufflesongs.mockwebserver.ResponseMocker
import br.com.felipeacerbi.shufflesongs.mockwebserver.ResponseMocker.Companion.STATUS_CODE_SUCCESS
import br.com.felipeacerbi.shufflesongs.mockwebserver.ServerTestRule
import org.hamcrest.core.IsNot.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SongsListFragmentTest {

    @get:Rule
    val serverTestRule = ServerTestRule()

    @Test
    fun whenFragmentIsOpenListShouldBeVisibleAndProgressNot() {
        setUpSuccessResponse()

        launchFragmentInContainer {
            SongsListFragment().apply {
                viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        Navigation.setViewNavController(requireView(), NavController(requireContext()))
                    }
                }
            }
        }

        viewIsVisible(R.id.songs_list)
        viewIsNotVisible(R.id.progress)
    }

    private fun setUpSuccessResponse() {
        val mocker = ResponseMocker(serverTestRule)
        mocker.mockResponse("lookup?id=909253", STATUS_CODE_SUCCESS,
            jsonResponse1
        )
        mocker.mockResponse("lookup?id=1171421960", STATUS_CODE_SUCCESS,
            jsonResponse2
        )
        mocker.mockResponse("lookup?id=358714030", STATUS_CODE_SUCCESS,
            jsonResponse3
        )
        mocker.mockResponse("lookup?id=1419227", STATUS_CODE_SUCCESS,
            jsonResponse4
        )
        mocker.mockResponse("lookup?id=264111789", STATUS_CODE_SUCCESS,
            jsonResponse5
        )
    }

    private fun viewIsVisible(@IdRes id: Int) {
        onView(withId(id)).check(matches(isDisplayed()))
    }

    private fun viewIsNotVisible(@IdRes id: Int) {
        onView(withId(id)).check(matches(not(isDisplayed())))
    }

    companion object {
        const val jsonResponse1 = "mocks/success_909253.json"
        const val jsonResponse2 = "mocks/success_1419227.json"
        const val jsonResponse3 = "mocks/success_264111789.json"
        const val jsonResponse4 = "mocks/success_358714030.json"
        const val jsonResponse5 = "mocks/success_1171421960.json"
    }
}
