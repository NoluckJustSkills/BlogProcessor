package com.assesment.truecaller.webcontentprocessor.data.repository

import android.util.Log
import com.assesment.truecaller.webcontentprocessor.core.util.Resource
import com.assesment.truecaller.webcontentprocessor.data.remote.WebContentApi
import com.assesment.truecaller.webcontentprocessor.domain.model.WebData
import com.assesment.truecaller.webcontentprocessor.domain.repository.WebContentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WebContentRepositoryImpl(private val webContentApi: WebContentApi) : WebContentRepository {
    override suspend fun getWebContent(): Resource<WebData> {
        var webData = WebData("No Data")
        try {
            val webDataContent = webContentApi.getContent()
            webData.webDataContent = webDataContent.body().toString()
        } catch (e: HttpException) {
            return Resource.Error(
                message = "Something went wrong,Check internet connection",
                data = webData
            )
        } catch (e: IOException) {
            return Resource.Error(
                message = "Could not reach server,Check internet connection",
                data = webData
            )
        }
        return Resource.Success(webData)
    }

}