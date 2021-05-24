package com.example.Entity

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object FacultyEntity:Table<Faculty>("faculty"){

    val id=int("id").primaryKey().bindTo { it.id }
    val name=varchar("name").bindTo { it.name }
}