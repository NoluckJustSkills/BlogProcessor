package com.assesment.truecaller.webcontentprocessor

import com.assesment.truecaller.webcontentprocessor.domain.usecase.GetEveryTenthCharacterUsecase
import com.assesment.truecaller.webcontentprocessor.domain.usecase.GetTenthCharacterUsecase
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetTenthCharacterUsecaseTest {
    private lateinit var testSubject: GetTenthCharacterUsecase

    @Before
    fun setUp() {
        testSubject = GetTenthCharacterUsecase(mockk())
    }

    @Test
    fun `test first tenth character is returned as string`() {
        val testString = "Truecaller Assesment"
        val firstTenthCharacter = "r"

        val target = testSubject.processTenthCharacterResult(testString)
        Assert.assertEquals(firstTenthCharacter, target)
    }
}