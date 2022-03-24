package com.assesment.truecaller.webcontentprocessor.domain.usecase

import com.assesment.truecaller.webcontentprocessor.core.util.Resource
import com.assesment.truecaller.webcontentprocessor.core.util.Result
import com.assesment.truecaller.webcontentprocessor.domain.repository.WebContentRepository

class GetEveryTenthCharacterUsecase(
    private val webContentRepository: WebContentRepository
) {
    suspend operator fun invoke(): Result<String> {
        var resourceResult: Result<String> = Result.Initial("");
        when (val serverResponse = webContentRepository.getWebContent()) {
            is Resource.Success -> {
                serverResponse.data?.let {
                    resourceResult =
                        Result.Success(processEveryTenthCharacterResult(it.webDataContent.toString()))
                }
            }
            is Resource.Error -> {
                resourceResult = Result.Failure(serverResponse.message)

            }
        }
        return resourceResult
    }

    fun processEveryTenthCharacterResult(webData: String): String {
        val chArray = webData.chunked(10)
        val charsBuilder = StringBuilder(3000)
        for (item in chArray) {
            charsBuilder.append(item.last())
        }
        return charsBuilder.toString()
    }

}



