package com.citraining.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.PropertyUnbounded;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.apache.sling.jcr.api.SlingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
@Component(metatype = true, label = "Component Mapping Servlet", 
description = "Servlet for converting template type with other resource type")
@Service(value = Servlet.class)
@Properties({
@Property(name = "sling.servlet.paths", value = "/bin/componentMapping"),	
@Property(name = "sling.servlet.methods", value = "GET")
})

public class ComponentMapping extends SlingAllMethodsServlet{
	 
	private static final long serialVersionUID = 1L;
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Reference
    private SlingRepository repository;
	@Reference
	QueryBuilder qbuilder;
	 	@Property(label = "A parameter", description = "Can be configured in /system/console/configMgr")
	    public static final String MY_PARAMETER = "myParameter";
	    private String myParameter;
	    
	    @Property(label = "Src path", description = "Select a Page path, which you want to convert from src template to dest template")
	    public static final String SRC_PATH = "srcPath";
	    private String srcPath;
	    
	    @Property(label = "Dest template",  description = "Whether or not to schedule this task concurrently")
	    public static final String DEST_TEMPLATE = "distTemplate";
	    private String distTemplate;
	    
	    @Property(unbounded=PropertyUnbounded.ARRAY,label = "Component Manpping from to",  description = "Plase specify which component need to change e.g 'collab/calendar/components/exportlink' to 'geometrixx-media/components/popular-articles'")
	    public static final String COMPONENT_MAPPINGS = "componentMappings";
	    private String[] componentMappings;
	    
	    Session session = null;
	   
	    
	    
	@Override
	protected void doGet(SlingHttpServletRequest req,
			 SlingHttpServletResponse resp) throws ServletException, IOException {
		Resource resource = req.getResource();		
		resp.getOutputStream().println(resource.toString());
		try{
			session =repository.loginAdministrative(null);	
			//session =repository.login(); get the current user session
			/* Query code */
			if(this.srcPath!=null && !"".equals(this.srcPath)){
				resp.getOutputStream().println("Path Whose Convert:"+this.srcPath);
				resp.getOutputStream().println("<br>New Template of Sales CQ:"+this.distTemplate);
				Node rootNode=session.getNode(this.srcPath);
				if(rootNode.hasNode("jcr:content")){
					Node templateNode=session.getNode(this.distTemplate);
					Node jcrNode=rootNode.getNode("jcr:content");
					boolean template=jcrNode.hasProperty("cq:template");
					if(template){
						resp.getOutputStream().println("<br>Changes Page Template Path:"+jcrNode.getPath());
						jcrNode.setProperty("cq:template", this.distTemplate);	
					//	jcrNode.getSession().save();
					}
					boolean reType=jcrNode.hasProperty("sling:resourceType");
					if(reType){
						resp.getOutputStream().println("<br>Changes Page Component Path:"+jcrNode.getPath());
						jcrNode.setProperty("sling:resourceType", templateNode.getNode("jcr:content").getProperty("sling:resourceType").getString());	
						//jcrNode.getSession().save();
					}
					jcrNode.getSession().save();
				}
				Map<String, String> map = new HashMap<String, String>();
				Query query = null;
				Node childrensubchild=null;
				for(int srcCopm=0;srcCopm<this.componentMappings.length;){
					resp.getOutputStream().println("<br>Component Name From src to destination:"+this.componentMappings[srcCopm]);					
			        map.put("path",this.srcPath );
			        map.put("type", "nt:unstructured");
			        map.put("property", "sling:resourceType");
			        map.put("property.value", this.componentMappings[srcCopm]);			       
				    query = qbuilder.createQuery(PredicateGroup.create(map), session);
				    query.setStart(0);
					query.setHitsPerPage(10000);
					SearchResult result = query.getResult();
	                Iterator<Node> childrensub = result.getNodes();
	                while (childrensub.hasNext()) {
	                	 childrensubchild = childrensub.next() ;
	                	resp.getOutputStream().println("<br>Changes Component From shop to Sales:"+childrensubchild.getPath()+"saved path New is::"+this.componentMappings[srcCopm+1]);
	                	log.debug("Path of the node:::"+childrensubchild.getPath());
	                	childrensubchild.setProperty("sling:resourceType", this.componentMappings[srcCopm+1]);
	                	//childrensubchild.getSession().save();
	                }
				    srcCopm=srcCopm+2;
				}
				childrensubchild.getSession().save();
			
				
			}
		}catch(RepositoryException e){
			resp.getOutputStream().println("error is"+e);
			e.printStackTrace();}
	}
	 private void configure(final Map<String, Object> config) {
	        this.myParameter = PropertiesUtil.toString(config.get(MY_PARAMETER), null);
	        this.srcPath = PropertiesUtil.toString(config.get(SRC_PATH), null);
	        this.distTemplate = PropertiesUtil.toString(config.get(DEST_TEMPLATE), null);
	        this.componentMappings = PropertiesUtil.toStringArray(config.get(COMPONENT_MAPPINGS), null);
	        log.debug("configure: myParameter='{}''", this.myParameter);
	        log.debug("second prperty is...");
	    }
	 @Activate
	    protected void activate(final Map<String, Object> config) {
	        configure(config);
	    }
}
