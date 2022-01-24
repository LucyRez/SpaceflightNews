package cs.hse.rezunik.cosmicnews

import cs.hse.rezunik.cosmicnews.db.Database
import cs.hse.rezunik.cosmicnews.db.DatabaseDriverFactory
import cs.hse.rezunik.cosmicnews.network.SpaceflightNewsAPI

class SpaceflightNewsSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = SpaceflightNewsAPI()

    @Throws(Exception::class) suspend fun getNews(forceReload: Boolean): List<Item> {
        val cached = database.getAllNews()
        return if (cached.isNotEmpty() && !forceReload) {
            cached
        } else {
            api.getAllNews().also {
                database.clearDatabase()
                database.createNews(it)
            }
        }
    }
}