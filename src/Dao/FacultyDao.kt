package com.example.Dao

import com.example.Database.DatabaseManager
import com.example.Model.FacultyModel

class FacultyDao {
    val databaseManager=DatabaseManager()

    fun getAllFaculty(): List<FacultyModel> {
        return databaseManager.getAllFaculty().map{FacultyModel(it.id,it.name)}
    }

    fun getFaculty(id:Int):FacultyModel?{
        val result=databaseManager.getStudent(id)?:return null
        return result.let{FacultyModel(it.id,it.name)}
    }

    fun createFaculty(input:FacultyModel): Int {
        return databaseManager.createFaculty(input)
    }

    fun updateFaculty(id:Int,input:FacultyModel): Boolean {
        return databaseManager.updateFaculty(id,input)
    }

    fun deleteFaculty(id:Int): Boolean {
        return databaseManager.deleteFaculty(id)
    }
}