package com.assesment.truecaller.webcontentprocessor

import com.assesment.truecaller.webcontentprocessor.domain.usecase.GetWordCountUsecase
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetWordCountUsecaseTest {

    private lateinit var testSubject: GetWordCountUsecase

    @Before
    fun setUp() {
        testSubject = GetWordCountUsecase(mockk())
    }

    @Test
    fun `test word count map is returned`() {
        val testString = "Truecaller Assesment Truecaller Assesment hello"
        val truecallerWordCount = 2
        val assesmentWordCount = 2
        val helloWordCount = 1

        val target = testSubject.buildWordMapFromString(testString)
        Assert.assertEquals(truecallerWordCount, target.get("Truecaller"))
        Assert.assertEquals(assesmentWordCount, target.get("Assesment"))
        Assert.assertEquals(helloWordCount, target.get("hello"))

    }
}