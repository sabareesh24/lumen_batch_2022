package com.training.resources;

import java.util.Collection;

import org.glassfish.grizzly.http.util.HttpStatus;

import com.example.demo.repos.StudentRepositoryImpl;
import com.training.exceptions.ElementNotFoundException;
import com.training.model.Student;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/students")
public class StudentResource {
	
	private StudentRepositoryImpl repo;
	public StudentResource() {
		super();
		this.repo=new StudentRepositoryImpl();
	}

	@Produces(value=MediaType.APPLICATION_JSON)
	@GET
	public Collection<Student> findAllStudent() {
		return repo.findAll();
	}
	
	@Produces(value=MediaType.APPLICATION_JSON)
	@GET
	@Path("/{key}")
	public Student findStudentById(@PathParam("key") int key){
		Student student=null;
		try {
			return repo.findById(key);
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
		}
		return student;
	}
	
	@Produces(value=MediaType.APPLICATION_JSON)
	@Consumes(value=MediaType.APPLICATION_JSON)
	@Path("/add")
	@POST
	public Response addStudent(Student entity) {
		boolean isAdded=repo.save(entity);
		if(isAdded)
		return Response.ok(entity).status(HttpStatus.CREATED_201.getStatusCode(), "Created").build();
		else
		return Response.ok(entity).status(HttpStatus.NOT_ACCEPTABLE_406.getStatusCode(), "Not created").build();
			
	}
	
	
}
