package cs.hse.rezunik.cosmicnews

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("newsSite")
    val newsSite: String,
    @SerialName("publishedAt")
    val timePublished: String,
    @SerialName("url")
    val articleUrl: String,
    @SerialName("imageUrl")
    val imageUrl: String
)