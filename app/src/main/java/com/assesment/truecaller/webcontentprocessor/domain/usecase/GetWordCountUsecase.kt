package com.assesment.truecaller.webcontentprocessor.domain.usecase

import android.util.Log
import com.assesment.truecaller.webcontentprocessor.core.util.Resource
import com.assesment.truecaller.webcontentprocessor.core.util.Result
import com.assesment.truecaller.webcontentprocessor.domain.repository.WebContentRepository
import java.util.*

class GetWordCountUsecase(private val webContentRepository: WebContentRepository) {
    suspend operator fun invoke(): Result<String> {
        var resourceResult: Result<String> = Result.Initial("");
        when (val serverResponse = webContentRepository.getWebContent()) {
            is Resource.Success -> {
                serverResponse.data?.let {
                    resourceResult = Result.Success(processResult(it.webDataContent.toString()))
                }
            }
            is Resource.Error -> {
                resourceResult = Result.Failure(serverResponse.message)

            }
        }
        return resourceResult
    }

    fun processResult(webData: String): String {
        val wordsFrequencyMap: MutableMap<String, Int> = buildWordMapFromString(webData)

        val charsBuilder = StringBuilder(10000)
        for ((key, value) in wordsFrequencyMap) {
            charsBuilder.append(key)
                .append(" -> ")
                .append(value)
                .append("\n")
        }
        return charsBuilder.toString()
    }

    fun buildWordMapFromString(webData: String): MutableMap<String, Int> {
        val wordsFrequencyMap: MutableMap<String, Int> = TreeMap(String.CASE_INSENSITIVE_ORDER)

        val wordsArray = webData.split("\\s+".toRegex())
            .filterNot { it.trim().isEmpty() }
        for (item in wordsArray) {
            if (item == "") continue
            var count =
                if (wordsFrequencyMap.containsKey(item)) wordsFrequencyMap[item]!! else 0
            count++
            wordsFrequencyMap[item] = count
        }
        return wordsFrequencyMap
    }
}


