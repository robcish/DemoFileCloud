package com.example.demofilecloud.api

import com.example.demofilecloud.Const
import com.example.demofilecloud.data.DataItem
import com.example.demofilecloud.data.GifDetailResponse
import com.example.demofilecloud.data.Images
import com.example.demofilecloud.data.Response
import com.giphy.sdk.core.models.Image
import com.giphy.sdk.core.models.Media
import io.reactivex.Observable

class ApiImplementation(
    private val apiInterface: ApiInterface
) {
    fun getTrendList(limit: Int): Observable<Response> {
        return apiInterface.getTrendList(Const.API_KEY, limit)
    }

    fun search(limit: Int, tag: String): Observable<Response> {
        return apiInterface.search(
            api_key = Const.API_KEY,
            tag = tag,
            limit = limit,
            offset = 0,
            rating = "pg-13",
            language = "en"
        )
    }

    fun getDetail(id: String): Observable<GifDetailResponse> {
        return apiInterface.getDetail(id, Const.API_KEY)
    }
}

internal fun DataItem.mapDataItemToMediaClass(): Media {
    return Media(
        id = this.id ?: "",
        images = this.images?.mapToCoreImages() ?: com.giphy.sdk.core.models.Images(),
        title = this.title
    )
}

private fun Images?.mapToCoreImages(): com.giphy.sdk.core.models.Images {
    return com.giphy.sdk.core.models.Images(
        fixedWidthSmall = Image(
            gifUrl = this?.fixedWidthSmall?.url,
            width = this?.fixedWidthSmall?.width?.toInt() ?: 0,
            height = this?.fixedWidthSmall?.height?.toInt() ?: 0
        ),
        downsized = Image(
            gifUrl = this?.downsized?.url,
            width = this?.downsized?.width?.toInt() ?: 0,
            height = this?.downsized?.height?.toInt() ?: 0
        )
    )
}