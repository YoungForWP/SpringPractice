package com.thoughtworks.gaia.student.endpoint;

import com.thoughtworks.gaia.student.StudentMapper;
import com.thoughtworks.gaia.student.entity.Kclass;
import com.thoughtworks.gaia.student.entity.Student;
import com.thoughtworks.gaia.student.model.StudentModel;
import com.thoughtworks.gaia.student.service.KclassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;

@Component
@Path("class")
@Api(value = "class", description = "Access to class resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class KclassEndPoint {
    @Autowired
    private KclassService kclassService;

    @Context
    private UriInfo uriInfo;

    @Path("/{kclassId}")
    @ApiOperation(value = "Get class by id", response = Kclass.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get class successfully"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GET
    public Response getKclass(@PathParam("kclassId") Long kclassId) {
        Kclass kclass = kclassService.getKclass(kclassId);
        return Response.ok().entity(kclass).build();
    }

    @Path("/{kclassId}/student/{studentId}")
    @ApiOperation(value = "Get class by id", response = Kclass.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get class successfully"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GET
    public Response getKclassStudent(@PathParam("kclassId") Long kclassId, @PathParam("studentId") Long studentId) {
        Student student = kclassService.getKclassStudent(kclassId, studentId);
        return Response.ok().entity(student).build();
    }

    @Path("/{kclassId}")
    @ApiOperation(value = "delete class by id", response = Kclass.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "delete class successfully"),
            @ApiResponse(code = 404, message = "not found")
    })
    @DELETE
    public Response deleteKclass(@PathParam("kclassId") Long kclassId) {
        kclassService.deleteKclass(kclassId);
        return Response.noContent().build();
    }

    @Path("/{kclassId}/student/{studentId}")
    @ApiOperation(value = "delete class by id", response = Kclass.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "delete class successfully"),
            @ApiResponse(code = 404, message = "not found")
    })
    @DELETE
    public Response deleteKclassStudent(@PathParam("kclassId") Long kclassId, @PathParam("studentId") Long studentId) {
        kclassService.deleteKclassStudent(kclassId, studentId);
        return Response.noContent().build();

    }

    @Path("{kclassId}/student")
    @ApiOperation(value = "add class student", response = Kclass.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "add class successfully"),
            @ApiResponse(code = 404, message = "not found")
    })
    @POST
    public Response addKclassStudent(@PathParam("kclassId") Long kclassId, Student student) throws URISyntaxException {
        Kclass kclass = kclassService.addKclassStudent(kclassId, student);
        return Response.created(new URI(uriInfo.getPath())).entity(kclass).build();
    }


    @ApiOperation(value = "add class Information", response = Kclass.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "add class successfully"),
            @ApiResponse(code = 404, message = "not found")
    })
    @POST
    public Response addKclass(Kclass kclass) throws URISyntaxException {
        Kclass kclassAdd = kclassService.addKclass(kclass);
        return Response.created(new URI("/gaia/class/" + kclassAdd)).entity(kclassAdd).build();
    }


    @Path("/{kclassId}")
    @ApiOperation(value = "update class Information", response = Kclass.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "update class successfully"),
            @ApiResponse(code = 404, message = "not found")
    })
    @PUT
    public Response updateKclass(@PathParam("kclassId") Long kclassId, Kclass kclass) throws URISyntaxException {
        kclass.setId(kclassId);
        return Response.ok().entity(kclassService.updateKclass(kclass)).build();
    }


    @Path("/{kclassId}/student/{studentId}")
    @ApiOperation(value = "update class student Information", response = Kclass.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "update class successfully"),
            @ApiResponse(code = 404, message = "not found")
    })
    @PUT
    public Response updateKclass(@PathParam("kclassId") Long kclassId, @PathParam("studentId") Long studentId, Student student) throws URISyntaxException {
        student.setId(studentId);
        Kclass kclass = kclassService.updateKclassStudent(kclassId, student);
        return Response.ok().entity(kclassService.updateKclass(kclass)).build();
    }

}


