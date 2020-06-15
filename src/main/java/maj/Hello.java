package maj;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Hello {

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String floop() {
        return "hello";
    }
}
