package com.example.globallogicapp.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.testing.withFragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.globallogicapp.R
import com.example.globallogicapp.helpers.Either
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class ProductsFragmentTest{

    @Test
    fun should_show_message_of_no_product_found() {
        val scenario = launchFragmentInContainer<ProductsFragment>(fragmentArgs = null)

        scenario.withFragment {
            this.updateView(Either.Right(listOf()))
        }

        onView(withId(R.id.empty_state)).check(matches(isDisplayed()))
        onView(withId(R.id.errorText)).check(matches(withText("No se obtuvo ningún producto")))
    }
}