package com.swensonhe.local.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TestEntity")
@Keep
class TestEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,


)