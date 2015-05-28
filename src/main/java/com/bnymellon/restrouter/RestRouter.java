/**
 * 
 */
package com.bnymellon.restrouter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;
import org.json.JSONException;
import org.json.JSONObject;

import com.bnymellon.model.Router;



@Path("/entrouter")
public class RestRouter {

	@GET
	@Produces("application/json")
	public Response convertFtoC() throws JSONException {

		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("Destination", "");
		jsonObject.put("DestinatioType", "");

		String result = "@Produces(\"application/json\") Output: \n\n Output: \n\n"
				+ jsonObject;
		return Response.status(200).entity(result).build();
	}

	@Path("{r}")
	@GET
	@Produces("application/json")
	public Response convertFtoCfromInput(@PathParam("r") String r)
			throws JSONException {

		JSONObject jsonObject = new JSONObject();
		String destination, destinationType;
		
		
		// instantiate a null session for final disposal check
		StatefulKnowledgeSession session = null;
		
		try {
			
			// seed a builder with our rules file from classpath
			KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
			builder.add(ResourceFactory.newClassPathResource("com/bnymellon/rules/determineRoute.drl"), ResourceType.DRL);
			if (builder.hasErrors()) {
			    throw new RuntimeException(builder.getErrors().toString());
			}
	
			// create a knowledgeBase from our builder packages
			KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
			knowledgeBase.addKnowledgePackages(builder.getKnowledgePackages());
			
			// initialize a session for usage
			session = knowledgeBase.newStatefulKnowledgeSession();
	
			// 
			Router rte = new Router(r,"","");
			FactHandle routerFact = session.insert(rte);
	        session.fireAllRules();
	        System.out.println("----------------");
	    	jsonObject.put("Destination", rte.getDestination());
			jsonObject.put("DestinationType", rte.getDestinationType());
	        
		} catch(Throwable t) {
            t.printStackTrace();
        } finally {
    		// if we still have a session at this point, we need to clean it up
        	if (session != null) { 
        		session.dispose();
        	}
        }
			        
			        
	

		String result = "@Produces(\"application/json\") Output: \n\n Output: \n\n"
				+ jsonObject;
		return Response.status(200).entity(result).build();
	}
}