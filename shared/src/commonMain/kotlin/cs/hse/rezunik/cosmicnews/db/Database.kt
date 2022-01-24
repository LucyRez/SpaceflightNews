package cs.hse.rezunik.cosmicnews.db

import cs.hse.rezunik.cosmicnews.CosmicNewsDB
import cs.hse.rezunik.cosmicnews.Item
import io.ktor.http.*

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = CosmicNewsDB(databaseDriverFactory.createDriver())
    private val dbQuery = database.cosmicNewsDBQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllNewsItems()
        }
    }

    internal fun getAllNews(): List<Item> {
        return dbQuery.selectAllNewsItems(::mapNewsSelecting).executeAsList()
    }

    private fun mapNewsSelecting(
        id: Long,
        title: String,
        newsSite: String,
        timePublished: String,
        articleUrl: String,
        imageUrl:  String

    ): Item {
        return Item(
            id = id.toInt(),
            title = title,
            newsSite = newsSite,
            timePublished = timePublished,
            articleUrl = articleUrl,
            imageUrl = imageUrl
        )
    }

    internal fun createNews(news: List<Item>) {
        dbQuery.transaction {
           news.forEach { item ->
                insertNewsItem(item)
            }
        }
    }

    private fun insertNewsItem(newsItem: Item) {
        dbQuery.insertNewsItem(
            id = newsItem.id.toLong(),
            title = newsItem.title,
            newsSite = newsItem.newsSite,
            timePublished = newsItem.timePublished,
            articleUrl = newsItem.articleUrl,
            imageUrl = newsItem.imageUrl
        )
    }

}

