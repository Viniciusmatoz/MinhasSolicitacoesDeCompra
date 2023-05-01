package com.vinicius.minhassolicitacoesdecompra

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate

class LocalDateConverter {
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLong(localDate: LocalDate?): Long? {
        return localDate?.toEpochDay()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromLong(value: Long?): LocalDate? {
        return value?.let { LocalDate.ofEpochDay(it) }
    }
}