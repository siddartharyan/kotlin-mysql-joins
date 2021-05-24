package com.example.Database

import com.example.Entity.Faculty
import com.example.Entity.FacultyEntity
import com.example.Entity.Student
import com.example.Entity.StudentEntity
import com.example.Model.*
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.firstOrNull
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class DatabaseManager {


    //config
    private val hostname="localhost:3306"
    private val databasename="school"
    private val username="root"
    private val password="954954"


    //database
    private val ktormDatabase: Database

    init{
        val jdbcUrl="jdbc:mysql://$hostname/$databasename?user=$username&password=$password&useSSL=false"
        ktormDatabase= Database.connect(jdbcUrl)
    }


    fun getAllStudents(): List<Student> {
        return ktormDatabase.sequenceOf(StudentEntity).toList()
    }

    fun getStudent(id:Int?): Student? {
        if(id==null){
            return null
        }
        return ktormDatabase.sequenceOf(StudentEntity).firstOrNull{it.id eq id}
    }

    fun createStudent(input: StudentDraft): StudentModel {
        val key=ktormDatabase.insertAndGenerateKey(StudentEntity){
            set(StudentEntity.name,input.name)
            set(StudentEntity.standard,input.standard)
        } as Int
        return StudentModel(key,input.name,input.standard)
    }

    fun updateStudent(id:Int,input:StudentDraft): Boolean {
        return ktormDatabase.update(StudentEntity){
            set(StudentEntity.name,input.name)
            set(StudentEntity.standard,input.standard)
            where{
                it.id eq id
            }
        }>0
    }

    fun deleteStudent(id:Int): Boolean {
        return ktormDatabase.delete(StudentEntity){
            it.id eq id
        }>0
    }

    fun getAllFaculty(): List<Faculty> {
        return ktormDatabase.sequenceOf(FacultyEntity).toList()
    }

    fun getFaculty(id:Int): Faculty? {
        return ktormDatabase.sequenceOf(FacultyEntity).firstOrNull{it.id eq id}
    }

    fun createFaculty(input:FacultyModel): Int {
        return ktormDatabase.insert(FacultyEntity){
            set(FacultyEntity.id,input.id)
            set(FacultyEntity.name,input.name)
        }
    }

    fun updateFaculty(id:Int,input:FacultyModel): Boolean {
        return ktormDatabase.update(FacultyEntity){
            set(FacultyEntity.name,input.name)
            where{it.id eq id}
        }>0
    }

    fun deleteFaculty(id:Int): Boolean {
        return ktormDatabase.delete(FacultyEntity){
            it.id eq id
        }>0
    }

    fun getAllStudentToFaculty(): List<StudentFaculty> {
        val result=ktormDatabase.from(FacultyEntity)
            .innerJoin(StudentEntity, on= FacultyEntity.id eq StudentEntity.standard)
            .select(StudentEntity.id,StudentEntity.name,FacultyEntity.name)
        return result.map{
            row->
            StudentFaculty(
                id=row[StudentEntity.id],
                student_name = row[StudentEntity.name],
                faculty_name = row[FacultyEntity.name]
            )
        }
    }

    fun getAllStudentbasedonFaculty(name:String): List<StudentBasedonFaculty> {
        return ktormDatabase.from(FacultyEntity)
            .innerJoin(StudentEntity,on=FacultyEntity.id eq StudentEntity.standard)
            .select(StudentEntity.id,StudentEntity.name)
            .where{FacultyEntity.name eq name}
            .map{
                    row ->
                StudentBasedonFaculty(
                    id=row[StudentEntity.id],
                    name=row[StudentEntity.name]
                )
            }
    }

}