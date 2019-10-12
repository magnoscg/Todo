package io.keepcoding.tareas.data.repository.local

import androidx.room.TypeConverter
import org.threeten.bp.Instant

class MyTypeConverters {

    @TypeConverter
    fun fromLocalDateTime(input: Instant): Long =
        input.toEpochMilli()

    @TypeConverter
    fun fromLong(input: Long): Instant =
            Instant.ofEpochMilli(input)

}