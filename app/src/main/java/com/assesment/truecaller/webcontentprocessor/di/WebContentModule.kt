package com.assesment.truecaller.webcontentprocessor.di

import com.assesment.truecaller.webcontentprocessor.data.remote.WebContentApi
import com.assesment.truecaller.webcontentprocessor.data.repository.WebContentRepositoryImpl
import com.assesment.truecaller.webcontentprocessor.domain.repository.WebContentRepository
import com.assesment.truecaller.webcontentprocessor.domain.usecase.GetEveryTenthCharacterUsecase
import com.assesment.truecaller.webcontentprocessor.domain.usecase.GetTenthCharacterUsecase
import com.assesment.truecaller.webcontentprocessor.domain.usecase.GetWordCountUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WebContentModule {

    @Provides
    fun provideGetEveryTenthCharacterUseCase(repository: WebContentRepository):
            GetEveryTenthCharacterUsecase {
        return GetEveryTenthCharacterUsecase(repository)
    }

    @Provides
    fun provideGetTenthCharacterUseCase(repository: WebContentRepository):
            GetTenthCharacterUsecase {
        return GetTenthCharacterUsecase(repository)
    }

    @Provides
    fun provideGetWordCountUsecase(repository: WebContentRepository):
            GetWordCountUsecase {
        return GetWordCountUsecase(repository)
    }

    @Provides
    fun provideCoroutineDispatcher():CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    fun provideWebContentRepository(
        api: WebContentApi
    ):WebContentRepository {
        return WebContentRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideApodApi(): WebContentApi {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
        return Retrofit.Builder()
            .baseUrl(WebContentApi.BASE_URL)
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(WebContentApi::class.java)
    }

}