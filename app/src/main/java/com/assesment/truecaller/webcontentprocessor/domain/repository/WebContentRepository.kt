package com.assesment.truecaller.webcontentprocessor.domain.repository

import com.assesment.truecaller.webcontentprocessor.core.util.Resource
import com.assesment.truecaller.webcontentprocessor.domain.model.WebData
import kotlinx.coroutines.flow.Flow

interface WebContentRepository {
  suspend  fun getWebContent(): Resource<WebData>
}