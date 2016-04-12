/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.UserFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author David
 */
@Path("newuser")
public class Register {

    @Context
    private UriInfo context;
    private Gson gson;

    /**
     * Creates a new instance of Register
     */
    public Register() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @POST
    @Consumes("application/json")
    public void newUser(String userJson) {
        entity.User user = gson.fromJson(userJson, entity.User.class);
        UserFacade.registerNewUser(user);
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/json") //"application/json"
    public String getBook(@PathParam("id") String userName) {
        entity.User user = UserFacade.getUser(userName);
        return gson.toJson(user);
    }
}
