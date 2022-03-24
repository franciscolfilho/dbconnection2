package dbconnection2;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;



/*
  @ApplicationPath annotation identifies this class as REST application 
  to automatic scanning process in servlet 3.0 containers. 
  It helps in making web.xml file almost empty – with no REST specific configuration at all.
*/
@ApplicationPath("/rest")
//Sets the path to base URL + /hello
@Path("/DbConnectionRest")
/*
 * The javax.ws.rs.core.Application class is a standard JAX-RS class that you may implement
 *  to provide information on your deployment
 */
public class DbConnectionRest extends Application {
//public class DbConnectionRest {	

	@GET
	@Path("/executeQuery")	
	@Produces(MediaType.TEXT_PLAIN)
	public Response executeQuery() {
		DbConnection dbConnection = new DbConnection(); 
		ResponseBuilder responseBuilder = null;	
		
		try {
			String output = dbConnection.testDbConnectionDS();
			System.out.println(output);
			responseBuilder = Response.status(Response.Status.OK).entity(output);

		} catch (Exception e) {
		   throw new WebApplicationException(Response
			     .status(Response.Status.INTERNAL_SERVER_ERROR)
			     .entity("Erro: " + e.getMessage()).build());    		   
	    }

		return responseBuilder.build();
	}

}