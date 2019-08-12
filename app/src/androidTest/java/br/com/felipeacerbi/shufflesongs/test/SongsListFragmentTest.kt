package br.com.felipeacerbi.shufflesongs.test

import androidx.annotation.IdRes
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import br.com.felipeacerbi.shufflesongs.R
import br.com.felipeacerbi.shufflesongs.list.view.SongsListFragment
import br.com.felipeacerbi.shufflesongs.mockwebserver.ResponseMocker
import br.com.felipeacerbi.shufflesongs.mockwebserver.ServerTestRule
import org.hamcrest.core.IsNot.not
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SongsListFragmentTest {

    @get:Rule
    val serverTestRule = ServerTestRule()

    private var idlingResource: IdlingResource? = null

    @Test
    fun whenRequestSuccessListShouldBeVisibleProgressGoneAndErrorGone() {
        setUpSuccessResponse()

        launchFragmentInContainer<SongsListFragment>(themeResId = R.style.Theme_AppCompat)
            .setUpIdlingResources()

        viewIsVisible(R.id.songs_list)
        viewIsNotVisible(R.id.progress)
        viewIsNotVisible(R.id.progress_background)
        viewIsNotVisible(R.id.error_message)
        viewIsNotVisible(R.id.error_background)
    }

    @Test
    fun whenRequestErrorListShouldBeVisibleProgressGoneAndErrorGone() {
        setUpErrorResponse()

        launchFragmentInContainer<SongsListFragment>(themeResId = R.style.Theme_AppCompat)
            .setUpIdlingResources()

        viewIsVisible(R.id.error_message)
        viewIsVisible(R.id.error_background)
        viewIsNotVisible(R.id.songs_list)
        viewIsNotVisible(R.id.progress)
        viewIsNotVisible(R.id.progress_background)
    }

    @Test
    fun whenClickShuffleListShouldBeVisibleProgressGoneAndErrorGone() {
        setUpSuccessResponse()

        launchFragmentInContainer<SongsListFragment>(themeResId = R.style.Theme_AppCompat)
            .setUpIdlingResources()

        clickOnView(R.id.action_shuffle)

        viewIsVisible(R.id.songs_list)
        viewIsNotVisible(R.id.progress)
        viewIsNotVisible(R.id.progress_background)
        viewIsNotVisible(R.id.error_message)
        viewIsNotVisible(R.id.error_background)
    }

    @After
    fun unregisterIdlingResources() {
        idlingResource?.let { IdlingRegistry.getInstance().unregister(it) }
    }

    private fun clickOnView(@IdRes id: Int) {
        onView(withId(id)).perform(click())
    }

    private fun viewIsVisible(@IdRes id: Int) {
        onView(withId(id)).check(matches(isDisplayed()))
    }

    private fun viewIsNotVisible(@IdRes id: Int) {
        onView(withId(id)).check(matches(not(isDisplayed())))
    }

    private fun setUpSuccessResponse() {
        val mocker = ResponseMocker(serverTestRule)
        mocker.mockResponse("lookup?id=909253", jsonResponse1)
        mocker.mockResponse("lookup?id=1171421960", jsonResponse2)
        mocker.mockResponse("lookup?id=358714030", jsonResponse3)
        mocker.mockResponse("lookup?id=1419227", jsonResponse4)
        mocker.mockResponse("lookup?id=264111789", jsonResponse5)
    }

    private fun setUpErrorResponse() {
        val mocker = ResponseMocker(serverTestRule)
        mocker.mockErrorResponse("lookup?id=909253")
        mocker.mockErrorResponse("lookup?id=1171421960")
        mocker.mockErrorResponse("lookup?id=358714030")
        mocker.mockErrorResponse("lookup?id=1419227")
        mocker.mockErrorResponse("lookup?id=264111789")
    }

    private fun FragmentScenario<SongsListFragment>.setUpIdlingResources() {
        onFragment { fragment ->
            idlingResource = fragment.getIdlingResource()
            IdlingRegistry.getInstance().register(idlingResource)
        }
    }

    companion object {
        const val jsonResponse1 = "mocks/success_909253.json"
        const val jsonResponse2 = "mocks/success_1419227.json"
        const val jsonResponse3 = "mocks/success_264111789.json"
        const val jsonResponse4 = "mocks/success_358714030.json"
        const val jsonResponse5 = "mocks/success_1171421960.json"
    }
}
