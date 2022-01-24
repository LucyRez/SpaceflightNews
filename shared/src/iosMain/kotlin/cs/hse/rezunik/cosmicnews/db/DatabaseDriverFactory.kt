package cs.hse.rezunik.cosmicnews.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import cs.hse.rezunik.cosmicnews.CosmicNewsDB

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(CosmicNewsDB.Schema, "test.db")
    }
}