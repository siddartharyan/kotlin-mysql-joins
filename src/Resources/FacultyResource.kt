package com.example.Resources

import com.example.Dao.FacultyDao
import com.example.Model.FacultyModel
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.FacultyResource(){

    val facultyDao= FacultyDao()
    route("/faculty"){

        get("/get/{id}"){
            val id=call.parameters["id"]?.toIntOrNull()
            if(id==null){
                call.respond(HttpStatusCode.BadRequest,"Student id cannot be null")
                return@get
            }
            val result:FacultyModel?=facultyDao.getFaculty(id)
            if(result?.id == null){
                call.respond(HttpStatusCode.NotFound,"entry not found")
                return@get
            }
            call.respond(result)
        }


        get("/get/all"){
            call.respond(facultyDao.getAllFaculty())
        }


        post("/create"){
            val input=call.receive(FacultyModel::class)
            if(input==null){
                call.respond(HttpStatusCode.BadRequest,"Student id cannot be null")
                return@post
            }
            call.respond(facultyDao.createFaculty(input))
        }


        put("/update/{id}"){
            val id=call.parameters["id"]?.toIntOrNull()
            if(id==null){
                call.respond(HttpStatusCode.BadRequest,"Student id cannot be null")
                return@put
            }
            val input:FacultyModel=call.receive(FacultyModel::class)
            if(input==null){
                call.respond(HttpStatusCode(400,"faculty model cannot be empty"))
                return@put
            }
            call.respond(facultyDao.updateFaculty(id,input))
        }


        delete("/delete/{id}"){
            val id=call.parameters["id"]?.toIntOrNull()
            if(id==null){
                call.respond(HttpStatusCode.BadRequest,"Student id cannot be null")
                return@delete
            }
            call.respond(facultyDao.deleteFaculty(id))
        }
    }
}

