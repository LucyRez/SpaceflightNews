package cs.hse.rezunik.cosmicnews.db

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import cs.hse.rezunik.cosmicnews.CosmicNewsDB

actual class DatabaseDriverFactory (private val context: Context){
   actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(CosmicNewsDB.Schema, context, "test.db")
    }
}