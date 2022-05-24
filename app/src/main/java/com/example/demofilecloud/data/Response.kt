package com.example.demofilecloud.data

import com.google.gson.annotations.SerializedName

data class Response(
	@field:SerializedName("pagination")
	val pagination: Pagination? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,
)

data class GifDetailResponse(
	@field:SerializedName("data")
	val data: DataItem? = null,
)

data class Pagination(
	@field:SerializedName("offset")
	val offset: Int? = null,

	@field:SerializedName("total_count")
	val totalCount: Int? = null,

	@field:SerializedName("count")
	val count: Int? = null
)

data class DataItem(

	@field:SerializedName("import_datetime")
	val importDatetime: String? = null,

	@field:SerializedName("images")
	val images: Images? = null,

	@field:SerializedName("embed_url")
	val embedUrl: String? = null,

	@field:SerializedName("trending_datetime")
	val trendingDatetime: String? = null,

	@field:SerializedName("bitly_url")
	val bitlyUrl: String? = null,

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("is_sticker")
	val isSticker: Int? = null,

	@field:SerializedName("source")
	val source: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("bitly_gif_url")
	val bitlyGifUrl: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("source_tld")
	val sourceTld: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("source_post_url")
	val sourcePostUrl: String? = null,

	@field:SerializedName("content_url")
	val contentUrl: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class Images(
	@field:SerializedName("fixed_width")
	val fixedWidth: ImageData? = null,

	@field:SerializedName("fixed_width_small")
	val fixedWidthSmall: ImageData? = null,

	@field:SerializedName("downsized")
	val downsized: ImageData? = null,
)

data class ImageData(

	@field:SerializedName("mp4")
	val mp4: String? = null,

	@field:SerializedName("size")
	val size: String? = null,

	@field:SerializedName("width")
	val width: String? = null,

	@field:SerializedName("mp4_size")
	val mp4Size: String? = null,

	@field:SerializedName("webp")
	val webp: String? = null,

	@field:SerializedName("webp_size")
	val webpSize: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: String? = null
)