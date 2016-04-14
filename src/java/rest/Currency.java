/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import facades.RateFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import static javax.ws.rs.client.Entity.entity;

/**
 * REST Web Service
 *
 * @author butwhole
 */
@Path("Currency")
public class Currency {

    @Context
    private UriInfo context;
    private Gson gson;

    /**
     * Creates a new instance of Currency
     */
    public Currency() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Retrieves representation of an instance of entity.Currency
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    @Path("/getRates")
    public void getJson() {
        //TODO return proper representation object
        
        
        
    }

    /**
     * PUT method for updating or creating an instance of Currency
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
