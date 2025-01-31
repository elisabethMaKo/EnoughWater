package com.android.example.enoughwater

import org.junit.Assert.assertEquals
import org.junit.Test

class MainActivityTest {

    @Test
    fun makeSureThatDeletingCupWorks(){
        // ARRANGE
        val counter = 5
        val expectedResult = 4

        // ACT
        val mainActivity = MainActivity()
        val result = mainActivity.decreaseCounter(counter)

        // ASSERT
        assertEquals(expectedResult, result)
    }
}