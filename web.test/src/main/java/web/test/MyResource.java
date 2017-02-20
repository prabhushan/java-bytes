package web.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/myresource")
public class MyResource {

	@GET
	@Path("name")
	public String name() {
		return "hello World";
		
	}
}
