package com.example.globallogicapp.ui

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.testing.withFragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.globallogicapp.R
import com.example.globallogicapp.data.model.Product
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class DetailsFragmentTest{

    private val product = Product(
        1,
        "Description",
        "https://picsum.photos/100/100?image=0",
        "Laptop 1"
    )

    @Test
    fun should_show_product_description() {
        val bundle = bundleOf("idProduct" to product.id)
        val scenario = launchFragmentInContainer<DetailsFragment>(
            fragmentArgs = bundle
        )

        scenario.withFragment {
            this.updateView(product)
        }

        onView(withId(R.id.title)).check(matches(withText(product.title)))
        onView(withId(R.id.description)).check(matches(withText(product.description)))
    }
}