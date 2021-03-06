/**
 * 
 */
package com.bny;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Test;


/**
 * @author asaran
 *
 */
public class RouterTest {

		
	/**
	 * simple runner test method that exercises each of our rules
	 */
	@Test
	public void testRules() {
		
		// instantiate a null session for final disposal check
		StatefulKnowledgeSession session = null;
		
		try {
			
			// seed a builder with our rules file from classpath
			KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
			builder.add(ResourceFactory.newClassPathResource("com/bny/rules/Router.drl"), ResourceType.DRL);
			if (builder.hasErrors()) {
			    throw new RuntimeException(builder.getErrors().toString());
			}
	
			// create a knowledgeBase from our builder packages
			KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
			knowledgeBase.addKnowledgePackages(builder.getKnowledgePackages());
			
			// initialize a session for usage
			session = knowledgeBase.newStatefulKnowledgeSession();
	
			// 
			//Router rt = new Router("F1","F2","F3");
			//FactHandle routerFact = session.insert(rt);
	        session.fireAllRules();
	        System.out.println("----------------");
	        
	       
	        
		} catch(Throwable t) {
            t.printStackTrace();
        } finally {
    		// if we still have a session at this point, we need to clean it up
        	if (session != null) { 
        		session.dispose();
        	}
        }
	}
}
