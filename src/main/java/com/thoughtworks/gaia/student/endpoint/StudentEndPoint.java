package com.thoughtworks.gaia.student.endpoint;

import com.thoughtworks.gaia.student.entity.Student;
import com.thoughtworks.gaia.student.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;


@Component
@Path("student")
@Api(value = "student", description = "Access to student resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentEndPoint {
    @Autowired
    private StudentService studentService;

    @Path("/{studentId}")
    @ApiOperation(value = "Get student by id", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get student successfully"),
            @ApiResponse(code = 404, message = "No student matches given id")
    })
    @GET
    public Response getStudent(@PathParam("studentId") Long studentId) {
        Student student = studentService.getStudent(studentId);
        return Response.ok().entity(student).build();
    }


    @Path("/{studentId}")
    @ApiOperation(value = "delete student by id", response = boolean.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "delete student successfully"),
            @ApiResponse(code = 404, message = "not found")
    })
    @DELETE
    public Response deleteStudent(@PathParam("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        return Response.noContent().build();
    }


    @ApiOperation(value = "add student Information", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "add student successfully"),
            @ApiResponse(code = 404, message = "not found")
    })
    @POST
    public Response addStudent(Student student) throws URISyntaxException {
        Student studentAdd = studentService.addStudent(student);
        return Response.created(new URI("/gaia/student/" + studentAdd)).entity(studentAdd).build();
    }


    @Path("/{studentId}")
    @ApiOperation(value = "update student Information", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "update student successfully"),
            @ApiResponse(code = 404, message = "not found")
    })
    @PUT
    public Response updateStudent(@PathParam("studentId") Long studentId, Student student) throws URISyntaxException {
        student.setId(studentId);
        Student student1 = studentService.updateStudent(student);
        return Response.ok().entity(student1).build();
    }

}


