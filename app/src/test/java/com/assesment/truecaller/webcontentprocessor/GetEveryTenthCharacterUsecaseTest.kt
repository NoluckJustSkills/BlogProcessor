package com.assesment.truecaller.webcontentprocessor

import com.assesment.truecaller.webcontentprocessor.domain.usecase.GetEveryTenthCharacterUsecase
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetEveryTenthCharacterUsecaseTest {

    private lateinit var testSubject: GetEveryTenthCharacterUsecase

    @Before
    fun setUp() {
        testSubject = GetEveryTenthCharacterUsecase(mockk())
    }

    @Test
    fun `test every tenth characters are returned as string`() {
        val testString = "Truecaller Assesment"
        val firstTenthCharacter = "rt"

        val target = testSubject.processEveryTenthCharacterResult(testString)
        Assert.assertEquals(firstTenthCharacter, target)
    }
}