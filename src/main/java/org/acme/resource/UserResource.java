package org.acme.resource;


import org.acme.model.User;
import org.acme.model.UserDto;
import org.acme.service.UserService;
import org.bouncycastle.openssl.PasswordException;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/users")
public class UserResource {

    @Inject
    UserService userService;



    /*
    förväntar sig anrop i form av JSON
    Typ: 
    {
        "username": "namhär",
        "password": "passwordhär"
    }

    */
    @POST
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/new-user")
    public Response newUser (UserDto userDto) {

        try {
            userService.createUser(userDto);
            return Response.ok("User was successfully created. You can now log in to see your API key.").build();

        } catch (PasswordException e) {
            return Response.status(400).entity(e.getMessage()).build(); //returnerar felmeddelande till registreringen
        } catch (IllegalArgumentException e) {
            return Response.status(400).entity(e.getMessage()).build(); // se ovan
        }

    }

    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/get-key")
    public Response getKey(UserDto userDto){
        try {
            String apiKey = userService.fetchApiKey(userDto.getUsername(), userDto.getPassword());
            return Response.ok(apiKey).build();
        } catch ( IllegalArgumentException e ) {
            return Response.status(400).entity(e.getMessage()).build();
        }
       
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/find-user") 
    public Response getUserByName(String username){

        try {
            User user = userService.returnUser(username);

            return Response.ok(user).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)  
                    .entity("User not found.")
                    .build();
        }

    }

}
