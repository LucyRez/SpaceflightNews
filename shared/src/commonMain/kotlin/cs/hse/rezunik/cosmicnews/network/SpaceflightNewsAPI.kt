package cs.hse.rezunik.cosmicnews.network

import cs.hse.rezunik.cosmicnews.Item
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class SpaceflightNewsAPI {
    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend fun getAllNews(): List<Item> {
        return httpClient.get(NEWS_ENDPOINT)
    }

    companion object {
        private const val NEWS_ENDPOINT = "https://api.spaceflightnewsapi.net/v3/articles?_limit=100"
    }
}