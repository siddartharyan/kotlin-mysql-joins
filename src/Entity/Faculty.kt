package com.example.Entity

import org.ktorm.entity.Entity

interface Faculty:Entity<Faculty>{

    companion object:Entity.Factory<Faculty>()

    val id:Int
    val name:String
}
