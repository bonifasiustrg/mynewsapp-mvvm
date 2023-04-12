package com.bonifasiustrg.newsappmvvm.db

import androidx.room.TypeConverter
import com.bonifasiustrg.newsappmvvm.models.Source

class Converters {
    @TypeConverter
    fun fromSource(source: Source): String? {
        return source.name  // we dont need to convert source.id as well
    }

    @TypeConverter
    fun toSource(name: String): Source{
        return Source(name, name)
    }
}