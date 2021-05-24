package com.example.Entity

import org.ktorm.entity.Entity

interface Student: Entity<Student> {

    companion object:Entity.Factory<Student>()

    val id:Int
    val name:String
    val standard:Int
}