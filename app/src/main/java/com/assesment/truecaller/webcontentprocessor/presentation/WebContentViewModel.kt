package com.assesment.truecaller.webcontentprocessor.presentation

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assesment.truecaller.webcontentprocessor.domain.usecase.GetEveryTenthCharacterUsecase
import com.assesment.truecaller.webcontentprocessor.core.util.Result
import com.assesment.truecaller.webcontentprocessor.domain.usecase.GetTenthCharacterUsecase
import com.assesment.truecaller.webcontentprocessor.domain.usecase.GetWordCountUsecase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class WebContentViewModel @Inject constructor(
    private val getEveryTenthCharacterUsecase: GetEveryTenthCharacterUsecase,
    private val getTenthCharacterUsecase: GetTenthCharacterUsecase,
    private val getWordCountUsecase: GetWordCountUsecase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    var everyTenthCharField: ObservableField<String> = ObservableField()
    var tenthCharField: ObservableField<String> = ObservableField()
    var wordCountField: ObservableField<String> = ObservableField()

    fun getEveryTenthCharacter() {
        viewModelScope.launch(dispatcher) {
            everyTenthCharField.set("Loading...")
            try {
                when (val everyTenthChar = getEveryTenthCharacterUsecase()) {
                    is Result.Success -> {
                        everyTenthCharField.set(everyTenthChar.data)
                    }
                    is Result.Failure -> {
                        everyTenthCharField.set(everyTenthChar.error)
                    }
                    is Result.Initial -> {
                        everyTenthCharField.set("")
                    }
                }
            } catch (ex: Exception) {
                everyTenthCharField.set(
                    ex.message
                        ?: ex.stackTraceToString()
                )
            }
        }
    }

    fun getTenthCharacter() {
        viewModelScope.launch(dispatcher) {
            tenthCharField.set("Loading...")
            try {
                when (val everyTenthChar = getTenthCharacterUsecase()) {
                    is Result.Success -> {
                        tenthCharField.set(everyTenthChar.data)
                    }
                    is Result.Failure -> {
                        tenthCharField.set(everyTenthChar.error)
                    }
                    is Result.Initial -> {
                        tenthCharField.set("")
                    }
                }
            } catch (ex: Exception) {
                tenthCharField.set(
                    ex.message
                        ?: ex.stackTraceToString()
                )
            }
        }
    }

    fun getWordCount() {
        viewModelScope.launch(dispatcher) {
            wordCountField.set("Loading...")
            try {
                when (val everyTenthChar = getWordCountUsecase()) {
                    is Result.Success -> {
                        wordCountField.set(everyTenthChar.data)
                    }
                    is Result.Failure -> {
                        wordCountField.set(everyTenthChar.error)
                    }
                    is Result.Initial -> {
                        wordCountField.set("")
                    }
                }
            } catch (ex: Exception) {
                wordCountField.set(
                    ex.message
                        ?: ex.stackTraceToString()
                )
            }
        }
    }

}