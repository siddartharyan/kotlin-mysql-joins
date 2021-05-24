package com.example.Resources

import com.example.Dao.StudentDao
import com.example.Model.StudentDraft
import com.example.Model.StudentModel
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Routing.StudentResource(){
    val studentDao=StudentDao()
    route("/student"){


        get("/get/{id}"){
            val id=call.parameters["id"]?.toIntOrNull()
            if(id==null){
                call.respond(HttpStatusCode.BadRequest,"Student id cannot be null")
                return@get
            }
            val result:StudentModel?=studentDao.getStudent(id)
            if(result?.id==null){
                call.respond(HttpStatusCode.NotFound,"entry not found")
                return@get
            }
            call.respond(result)
        }


        get("/get/all"){
            call.respond(studentDao.getAllStudents())
        }


        post("/create"){
            val input:StudentDraft?=call.receive(StudentDraft::class)
            if(input==null){
                call.respond(HttpStatusCode.BadGateway,"Student id cannot be null")
                return@post
            }
            call.respond(studentDao.createStudent(input))
        }


        put("/update/{id}"){
            val id=call.parameters["id"]?.toIntOrNull()
            if(id==null){
                call.respond(HttpStatusCode.BadRequest,"Student id cannot be null")
                return@put
            }
            val input:StudentDraft?=call.receive(StudentDraft::class)
            if(input==null){
                call.respond(HttpStatusCode(400,"Student model cannot be null"))
                return@put
            }
            call.respond(studentDao.updateStudent(id,input))
        }


        delete("/delete/{id}"){
            val id=call.parameters["id"]?.toIntOrNull()
            if(id==null){
                call.respond(HttpStatusCode.BadRequest,"Student id cannot be null")
                return@delete
            }
            call.respond(studentDao.deleteStudent(id))
        }

        get("/faculty"){
            call.respond(studentDao.getAllStudentToFaculty())
        }

        get("/faculty/{name}"){
            val name:String?=call.parameters["name"]
            if(name==null || name.length==0){
                call.respond(HttpStatusCode.BadRequest,"faculty name cannot be empty")
                return@get
            }
            call.respond(studentDao.getAllStudentsBasedonFaculty(name))
        }
    }
}
