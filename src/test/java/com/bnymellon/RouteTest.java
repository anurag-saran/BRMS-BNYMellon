/**
 * 
 */
package com.bnymellon;

import java.math.BigDecimal;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;
import org.junit.Test;




import com.bnymellon.model.Router;

import junit.framework.TestCase;

/**
 * @author asaran
 *
 */
public class RouteTest extends TestCase {

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
			Router rte = new Router("Function","Function","Function");
			FactHandle routerFact = session.insert(rte);
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
