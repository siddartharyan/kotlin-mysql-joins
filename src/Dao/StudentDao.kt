package com.example.Dao

import com.example.Database.DatabaseManager
import com.example.Model.StudentBasedonFaculty
import com.example.Model.StudentDraft
import com.example.Model.StudentFaculty
import com.example.Model.StudentModel

class StudentDao {
    val databaseManager=DatabaseManager()

    fun getAllStudents(): List<StudentModel> {
        return databaseManager.getAllStudents().map{StudentModel(it.id,it.name,it.standard)}
    }

    fun getStudent(id:Int?): StudentModel?{
        val result= databaseManager.getStudent(id) ?: return null
        return result.let{ StudentModel(it.id, it.name, it.standard) }
    }

    fun createStudent(input:StudentDraft): StudentModel {
        return databaseManager.createStudent(input)
    }

    fun updateStudent(id:Int,input:StudentDraft): Boolean {
        return databaseManager.updateStudent(id,input)
    }

    fun deleteStudent(id:Int): Boolean {
        return databaseManager.deleteStudent(id)
    }

    fun getAllStudentToFaculty(): List<StudentFaculty> {
        return databaseManager.getAllStudentToFaculty()
    }

    fun getAllStudentsBasedonFaculty(name:String): List<StudentBasedonFaculty> {
        return databaseManager.getAllStudentbasedonFaculty(name)
    }
}