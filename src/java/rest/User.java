package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.RateFacade;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("demouser")
@RolesAllowed("User")
public class User {
private Gson gson;
    public User() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSomething() {
        return "{\"message\" : \"This message was delivered via a REST call accesible by only authenticated USERS\"}";
    }

    @GET
    @Path("/firstRun")
    public void firstRun() {
        RateFacade.getRates();
    }

    @GET
    @Produces("application/json")
    @Path("/getRates")
    public String getJson() {
        return gson.toJson(RateFacade.RatesToday());
    }
}
