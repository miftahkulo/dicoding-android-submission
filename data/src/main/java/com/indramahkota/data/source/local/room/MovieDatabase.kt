package com.indramahkota.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.indramahkota.data.BuildConfig
import com.indramahkota.data.source.local.entity.MovieEntity
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(
    entities = [
        MovieEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var instance: MovieDatabase? = null
        private val LOCK = Any()

        private val passphrase: ByteArray = SQLiteDatabase.getBytes(BuildConfig.SECRET_KEY.toCharArray())
        val factory = SupportFactory(passphrase)

        operator fun invoke(context: Context, name: String) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context, name).also { instance = it }
        }

        private fun buildDatabase(context: Context, name: String) = Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            name
        )
            .fallbackToDestructiveMigrationFrom(1)
            .openHelperFactory(factory)
            .build()
    }
}