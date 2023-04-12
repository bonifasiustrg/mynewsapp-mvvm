package com.bonifasiustrg.newsappmvvm.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bonifasiustrg.newsappmvvm.models.Article

@Database(
    entities = [Article::class],
    version = 1
)

@TypeConverters(Converters::class)
// This anotate will tell Room how data type like Source (in Article class)
// should be interpreted and convert that Source to String for exmaple, dan sebaliknya

// Database always abstract class
abstract class ArticleDatabase: RoomDatabase() {
    abstract fun getArticleDao(): ArticleDAO

    companion object {
        @Volatile //other threats can immediately see when a threat changes this instance so
        private var instance: ArticleDatabase? = null
        private val LOCK = Any() //that we really make sure that there is only a single instance of our database

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabaseInstance(context).also {
                instance = it
            }
        }

        private fun createDatabaseInstance(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()
    }
}