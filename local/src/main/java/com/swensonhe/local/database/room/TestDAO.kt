package com.syngenta.local.database.room

import androidx.room.Dao

import androidx.room.Query
import com.swensonhe.local.model.TestEntity


@Dao
interface TestDAO {
    @Query("SELECT * FROM TestEntity")
    fun getAllTests(): List<TestEntity>
}