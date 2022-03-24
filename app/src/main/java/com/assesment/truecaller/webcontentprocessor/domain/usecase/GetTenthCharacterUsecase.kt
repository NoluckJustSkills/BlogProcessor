package com.assesment.truecaller.webcontentprocessor.domain.usecase

import com.assesment.truecaller.webcontentprocessor.core.util.Resource
import com.assesment.truecaller.webcontentprocessor.core.util.Result
import com.assesment.truecaller.webcontentprocessor.domain.repository.WebContentRepository

class GetTenthCharacterUsecase(
    val webContentRepository: WebContentRepository
) {
    suspend operator fun invoke(): Result<String> {
        var resourceResult: Result<String> = Result.Initial("");
        when (val serverResponse = webContentRepository.getWebContent()) {
            is Resource.Success -> {
                serverResponse.data?.let {
                    resourceResult = Result.Success(processTenthCharacterResult(it.webDataContent.toString()))
                }
            }
            is Resource.Error -> {
                resourceResult = Result.Failure(serverResponse.message)

            }
        }
        return resourceResult
    }

    fun processTenthCharacterResult(webData: String) = webData[9].toString()

}
